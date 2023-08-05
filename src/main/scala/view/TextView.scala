package view
import presentation.data._

case class TextView(source: Presentation) {
  // TODO: add caching

  def format(f: Format): TextView = TextView(f.process(source))

  def asString: String = {
    import source._
    import separators._
    val str = new StringBuilder()

    categories foreach { category =>
      str append s"${category.header}${headerSep.value}\n"
      category.lines map {
        case NewLine => // for now ignoring
        case WordLine(first, second) =>
          str append s"${first}${lineSep.value}${second}\n"
      }
      str append "\n"
    }
    str.toString
  }
}

sealed trait Format {
  def process(content: Presentation): Presentation
}

object Format {
  // aliases
  val LearningPart = OnlyFirstPart
  val TranslatedPart = OnlySecondPart
}

object DefaultFormat extends Format {
  def process(content: Presentation): Presentation = content
}

object OnlyFirstPart extends Format {
  def process(content: Presentation): Presentation = {
    content.transformContent(w => WordLine(w.first, " "))
  }
}

object OnlySecondPart extends Format {
  def process(content: Presentation): Presentation = {
    content.transformContent(w => WordLine(" ", w.second))
  }
}
