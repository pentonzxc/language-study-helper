package presentation

import scala.util.Random
import scala.annotation.unused

/*
 TODO:
       Thinking about use this module , without extends PresentationModule
       Suppose , it's unnecessary in having strong couple like Data => DataTransformer
       Want to somehow attach that module as helper, not as data module first value
       For now, move this whole module to PresentationModule
       But if it will grow , what i should do?
 */

@unused
trait TransformerModule {
  self: PresentationModule =>

  object Transformer {
    def shuffle(content: Presentation): Presentation = {
      val lines: List[WordLine] = content.categories
        .flatten(_.lines)
      Presentation(
        Category("Shuffled", Random.shuffle(lines)) :: Nil,
        content.separators
      )
    }

    def shuffleIntoCategories(content: Presentation): Presentation = {
      Presentation(
        Random
          .shuffle(content.categories)
          .view
          .map { category =>
            Category(category.header, Random.shuffle(category.lines))
          }
          .toList,
        content.separators
      )
    }
  }
}
