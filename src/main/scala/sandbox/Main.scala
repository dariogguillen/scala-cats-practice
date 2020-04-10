package sandbox

import cats.instances.string._
import cats.instances.int._
import cats.syntax.semigroup._

object Main extends App {
  println("Hello " |+| "Cats!")
  println(2 |+| 3)
}
