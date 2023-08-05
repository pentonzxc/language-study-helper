import parser.file._
import presentation.data._
import view._
import util.FileUtil._
import java.io.File
import scala.util.matching.Regex
import java.io.PrintWriter

object App {
  def main(args: Array[String]): Unit = {
    val parser = new FileWithCategoriesParser()
    val content = parser
      .parse(source = new File("words.txt"))(
        HeaderSeparator(":"),
        LineSeparator(" - ")
      )

    val shuffled = Transformer.shuffle(content)
    val view = TextView(content)

    writeToFile(new File("somefile.txt")) { w =>
      w.write(view.format(Format.LearningPart).asString)
    }
  }
}
