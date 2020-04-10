package sandbox

trait Json
case class JsonObject(value: Map[String, Json]) extends Json
case class JsonString(value: String) extends Json
case class JsonArr(value: Seq[Json]) extends Json
case class JsonNumber(value: Double) extends Json
case object JsonNull extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

object JsonWriterInstances {
  import JsonSyntax._

  implicit lazy val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JsonString(value)
  }

  implicit lazy val objectWriter: JsonWriter[Map[String, Json]] =
    new JsonWriter[Map[String, Json]] {
      override def write(value: Map[String, Json]): Json = JsonObject(value)
    }

  implicit lazy val arrWriter: JsonWriter[Seq[Json]] =
    new JsonWriter[Seq[Json]] {
      override def write(value: Seq[Json]): Json = JsonArr(value)
    }

  implicit lazy val numberWriter: JsonWriter[Double] = new JsonWriter[Double] {
    override def write(value: Double): Json = JsonNumber(value)
  }

  implicit lazy val nullWriter: JsonWriter[Null] = new JsonWriter[Null] {
    override def write(value: Null): Json = JsonNull
  }

  implicit lazy val personWriter: JsonWriter[Person] = new JsonWriter[Person] {

    override def write(value: Person): Json = JsonObject(
      Map(
        "name"     -> value.name.asJson,
        "lastName" -> value.lastName.asJson,
        "age"      -> value.age.toDouble.asJson
      )
    )
  }

  implicit def optionWriter[A](implicit jsonWriter: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {

      override def write(value: Option[A]): Json = value match {
        case Some(value) => jsonWriter.write(value)
        case None        => JsonNull
      }
    }
}

object JsonSyntax {

  implicit class JsonWriterOps[A](value: A) {
    def asJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }
}

case class Person(name: String, lastName: String, age: Int)
