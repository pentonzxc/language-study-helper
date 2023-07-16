import java.util.HashMap
import scala.collection.mutable.ListBuffer
import java.io.File
import java.nio.charset.StandardCharsets
object App {
  def main(args: Array[String]): Unit = {
    val source = io.Source.fromFile("words.txt")
    val builders: ListBuffer[CategoryBuilder] = ListBuffer()
    var currentBuilder: CategoryBuilder = null

    for (line <- source.getLines()) {
      if (Category.isHeader(line)) {
        val newBuilder = CategoryBuilder(line, ListBuffer.empty[WordLine])
        builders += newBuilder
        currentBuilder = newBuilder
      } else {
        currentBuilder.lines += WordLine(line)
      }
    }

    val categories = builders.map(Category.fromCategoryBuilder)

    val StudyWordsFile = new File("study.txt")
    val TranslationWordsFile = new File("translation.txt")
    if (StudyWordsFile.exists()) {
      StudyWordsFile.delete()
    }
    if (TranslationWordsFile.exists()) {
      TranslationWordsFile.delete()
    }
    StudyWordsFile.createNewFile()
    TranslationWordsFile.createNewFile()

    printToFile(StudyWordsFile) { writer =>
      categories.foreach { category =>
        writer.println(category.header.toUpperCase())
        category.lines.map(_.studyWord).foreach(writer.println)
      }
    }

    printToFile(TranslationWordsFile) { writer =>
      categories.foreach { category =>
        writer.println(category.header.toUpperCase())
        category.lines.map(_.studyWordTranslation).foreach(writer.println)
      }
    }

  }

  object Category {
    val HeaderMarker = ":" (0)
    def isHeader(header: Header) = HeaderMarker == header.last
    def fromCategoryBuilder(creator: CategoryBuilder) =
      Category(creator.header, creator.lines.toList)
  }

  type Header = String
  case class Category(header: Header, lines: List[WordLine])
  case class CategoryBuilder(header: Header, lines: ListBuffer[WordLine])
  case class WordLine(line: String) {
    private val parts: Array[String] = line.split(" - ")
    // assert(parts.length == 2)
    def studyWord: String = parts.head
    def studyWordTranslation: String = parts.last
  }

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) }
    finally { p.close() }
  }
}
