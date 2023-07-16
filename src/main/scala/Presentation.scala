// trait PresentationModule {

//   def builder: Builder.ContentBuilder = Builder.ContentBuilder()

//   object Builder {
//     import scala.collection.mutable.ListBuffer

//     case class ContentBuilder() {
//       private val content: ListBuffer[CategoryBuilder] =
//         ListBuffer.empty[CategoryBuilder]
//       private var currentHeader: String = ""

//       def addLine(line: WordLine): Unit = currentHeader match {
//         case str: String if str.nonEmpty => content.head.lines += line
//       }

//       def addHeader(header: String): Unit =
//         content += CategoryBuilder(header)

//       def build: Content =
//         Content(
//           content.map(c => Category(c.header, c.lines.toList)).toList
//         )
//     }

//     private[Presentation] case class CategoryBuilder(
//         val header: String,
//         val lines: ListBuffer[WordLine] = ListBuffer.empty[WordLine]
//     )
//   }

//   case class Category(val header: String, val lines: List[WordLine])

//   case class Content(val lines: List[Category])

//   case class WordLine(studyWord: String, studyWordTranslation: String)
// }
