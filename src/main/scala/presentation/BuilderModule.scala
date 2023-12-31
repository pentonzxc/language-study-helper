package presentation

trait BuilderModule {
  self: PresentationModule =>
  import collection.mutable.ListBuffer

  case class ContentBuilder() {

    private var currentCategory: CategoryBuilder = CategoryBuilder("")

    private val content: ListBuffer[CategoryBuilder] =
      ListBuffer(currentCategory)

    def addLine(line: WordLine): Unit = currentCategory.lines += line

    def addHeader(header: String): Unit = {
      currentCategory = CategoryBuilder(header)
      content += currentCategory
    }

    def build(
        hSep: HeaderSeparator,
        lSep: LineSeparator
    ): Presentation = Presentation(
      (content.toList match {
        case xs :: xz if xs.lines.size == 0 => xz
        case s                              => s
      }).map(c => Category(c.header, c.lines.toList)),
      Separator.Pair(headerSep = hSep, lineSep = lSep)
    )
  }

  case class CategoryBuilder(
      val header: String,
      val lines: ListBuffer[WordLine] = ListBuffer.empty[WordLine]
  )
}
