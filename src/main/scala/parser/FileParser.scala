package parser
package file
import presentation.data._

import java.io.File
import scala.util.matching.Regex
import util.FileUtil

abstract class FileParser extends Parser {

  val MatchErrorMessage = "UNKNOWN PATTERN"
  override protected type Source = File

  protected[this] def readFile(file: Source): Iterator[String] = {
    val source = io.Source.fromFile(file)
    source.getLines()
  }
}

// some default impl

class FileWithCategoriesParser extends FileParser {

  private def regexForLine(separator: LineSeparator): Regex = {
    s"(.*)${separator.value}(.*)".r
  }

  private def regexForHeader(separator: HeaderSeparator): Regex = {
    s"(.*)${separator.value}".r
  }

  override def parse(
      source: Source
  )(
      headerSeparator: HeaderSeparator,
      lineSeparator: LineSeparator
  ): Presentation = {
    val builder = ContentBuilder()
    val lineRegex = regexForLine(lineSeparator)
    val headerRegex = regexForHeader(headerSeparator)

    def isLine(target: String): Boolean =
      target contains lineSeparator.value
    def isHeader(target: String): Boolean =
      target contains headerSeparator.value

    for (line <- FileUtil.stripFileContent(source)) {
      lazy val Array(f, s) = line split lineSeparator.value

      line match {
        case headerRegex(f)  => builder.addHeader(f)
        case lineRegex(f, s) => builder.addLine(WordLine(f, s))
        case s if s.isBlank  => builder.addLine(NewLine)
        case _ => builder.addLine(WordLine(line, MatchErrorMessage))
      }
    }

    builder.build(headerSeparator, lineSeparator)
  }
}
