package traits

import org.scalatest._

class OrderedPointTests extends FlatSpec with MustMatchers {

  import OrderedPoint._

  // Trait Exercise 1
  "small ordered point" should "be smaller than big ordered point" in {
    (orderedPointSmall < orderedPointBig) must be(true)
  }

  "big ordered point" should "be greater than small ordered point" in {
    (orderedPointBig > orderedPointSmall) must be(true)
  }

  "small ordered point" should "be less than or equal to itself" in {
    (orderedPointSmall <= orderedPointSmall) must be(true)
  }

  "big ordered point" should "be greater than or equal to small ordered point" in {
    (orderedPointBig >= orderedPointSmall) must be(true)
  }


}