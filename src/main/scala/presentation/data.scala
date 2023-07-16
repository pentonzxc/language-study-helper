package presentation

package object data extends PresentationModule with BuilderModule {

  sealed trait Separator {
    val value: String;
  }
  case class HeaderSeparator(value: String) extends Separator
  case class LineSeparator(value: String) extends Separator
}
