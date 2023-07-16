import parser.file._
import presentation.data._
import view.TextView
import java.io.File
import scala.util.matching.Regex

object App2 {
  def main(args: Array[String]): Unit = {
    val parser = new FileWithCategoriesParser()
    val content = parser
      .parse(source = new File("words.txt"))(
        HeaderSeparator(":"),
        LineSeparator(" - ")
      )

    val list = TextView.from(content)(
      HeaderSeparator(":"),
      LineSeparator(" - ")
    )
  }
}
