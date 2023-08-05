package parser
import presentation.data._

abstract class Parser {
  protected type Source;

  def parse(
      from: Source
  )(
      headerSeparator: HeaderSeparator,
      lineSeparator: LineSeparator
  ): Presentation
}
