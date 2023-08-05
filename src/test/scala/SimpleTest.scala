import org.scalatest._
import org.scalatest.flatspec._
// import org.scalatest.matchers.must.Matchers._
import org.scalatest.matchers.should.Matchers._
import org.scalacheck.Properties
import org.scalacheck.Prop._

class SimpleTest extends AnyFlatSpec {

  behavior of "my-first-test"

  it should "work" in {
    List(1, 2) should have size 2
  }

  /*
 TODO: What i should test list:
    1) FileParser bound test on only lineSeparator || headerSeparator , and unravel why doesn't print error on 'la casa' and similar cases, check if work my checkRegex(f , _ , s), namely _
    2) Maybe transformation....
   */
}
