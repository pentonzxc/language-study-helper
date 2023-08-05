package presentation

trait PresentationModule {

  case class Category(
      header: String,
      lines: List[WordLine]
  )

  case class Presentation(
      categories: List[Category],
      separators: Separator.Pair
  ) {
    def transformContent(f: WordLine => WordLine): Presentation = {
      Presentation(
        categories.map(c =>
          Category(
            c.header,
            c.lines.map(f)
          )
        ),
        separators
      )
    }
  }

  case class WordLine(first: String, second: String)

  object NewLine extends WordLine("", "")

  sealed trait Separator {
    val value: String;
  }

  object Separator {
    case class Pair(
        headerSep: HeaderSeparator,
        lineSep: LineSeparator
    )
  }

  case class HeaderSeparator(value: String) extends Separator
  case class LineSeparator(value: String) extends Separator
}
