package presentation

private[presentation] trait PresentationModule {

  case class Category(header: String, lines: List[WordLine])

  case class Content(data: List[Category])

  case class WordLine(first: String, second: String)

}
