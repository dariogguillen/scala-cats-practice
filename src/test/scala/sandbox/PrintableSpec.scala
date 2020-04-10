package sandbox

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import sandbox.Printable.PrintableInstances._
import sandbox.Printable._

class PrintableSpec extends AnyWordSpecLike with Matchers {

  "Printable" should {
    "to string" in {
      Printable.format(10) shouldBe 10.toString
    }
    "print " in {
      Printable.print(10)
    }
  }
}
