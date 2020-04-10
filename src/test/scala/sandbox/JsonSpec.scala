package sandbox

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import JsonWriterInstances._
import JsonSyntax._

class JsonSpec extends AnyWordSpecLike with Matchers {

  "Json" should {
    "write a json from string" in {
      "Hola".asJson shouldBe JsonString("Hola")
      Json.toJson("Hola") shouldBe JsonString("Hola")
    }
    "write a json from double" in {
      1.0.asJson shouldBe JsonNumber(1.0)
      Json.toJson(10.0) shouldBe JsonNumber(10.0)
    }
    "write a json from person" in {
      val person = Person("name", "lastName", 10)
      val personJson = JsonObject(
        Map(
          "name"     -> person.name.asJson,
          "lastName" -> person.lastName.asJson,
          "age"      -> person.age.toDouble.asJson
        )
      )

      person.asJson shouldBe personJson
      Json.toJson(person) shouldBe personJson
    }
    "write a json from option" in {
      Option("hola").asJson shouldBe JsonString("hola")
      Option(1.0).asJson shouldBe JsonNumber(1.0)
    }
  }
}
