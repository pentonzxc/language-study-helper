import munit.FunSuite
class SimpleTest extends FunSuite {

  test("simple test") {
    println("All good")
    assertEquals(1, 1)
  }

  /*
 TODO: What i should test list:
    1) FileParser bound test on only lineSeparator || headerSeparator , and unravel why doesn't print error on 'la casa' and similar cases, check if work my checkRegex(f , _ , s), namely _
    2) Maybe transformation....

   */
}
