package view
import presentation.data._

case class TextView(text: String)

object TextView {
  def from(
      content: Content
  )(
      headerSeparator: HeaderSeparator,
      lineSeparator: LineSeparator
  ): TextView = {
    var str: StringBuilder = new StringBuilder()

    content.data.foreach { category =>
      str ++= s"${category.header}${headerSeparator.value}\n"
      category.lines.map { line =>
        str ++= s"${line.first}${lineSeparator.value}${line.second}\n"
      }
    }

    TextView(str.toString)
  }
}
