// import java.io.File

// import Presentation.Builder._;
// import Presentation.WordLine

// object Parser {

//   class FileWithCategoriesParser extends FileParser {

//     // TODO: think about defining separate isHeader and isLine logic

//     override def parse(
//         from: Source
//     )(lineSeparator: String, headerSeparator: String): Presentation.Content = {
//       val builder = ContentBuilder()

//       def isLine(target: String): Boolean = target contains lineSeparator
//       def isHeader(target: String): Boolean = target contains headerSeparator

//       for (line <- readFile(from)) {
//         lazy val Array(study, translation) = line split lineSeparator
//         line match {
//           case _ if isHeader(line) => builder.addHeader(line)
//           case _ if isLine(line) =>
//             builder.addLine(WordLine(study, translation))
//         }
//       }

//       builder.build
//     }
//   }

//   abstract class FileParser extends Parser {
//     override type Source = File

//     private[Parser] def readFile(file: Source): Iterator[String] = {
//       val source = io.Source.fromFile(file)
//       source.getLines()
//     }
//   }
// }

// abstract class Parser {
//   type Source;

//   def parse(
//       from: Source
//   )(lineSeparator: String, headerSeparator: String): Presentation.Content
// }
