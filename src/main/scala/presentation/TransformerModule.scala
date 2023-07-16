package presentation

import scala.util.Random

trait TransformerModule {
  self: PresentationModule =>

  def shuffle(content: Content): Content = {
    val lines: List[WordLine] = content.data flatten (_.lines)
    Content(Category("", Random.shuffle(lines)) :: Nil)
  }

  def shuffleIntoCategories(content: Content): Content = {
    Content(
      Random
        .shuffle(content.data)
        .view
        .map { category =>
          Category(category.header, Random.shuffle(category.lines))
        }
        .toList
    )
  }
}
