package lectures.part5patternmatching

import scala.util.Random

object PatternMatching extends App {
  //switch on steroids
  val random = Random
  val x = random.nextInt(10)
  val description = x match {
    case 1 => "The one"
    case 2 => "Double or nothing"
    case 3 => "Third time's the charm"
    case _ => "Some other value"
  }

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n, and I can't drink in the US" // guard
    case Person(n, a) => s"Hi, my name is $n, and I am $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
  1. Cases are matched in order
  2. If no cases match, you get scala.matcherror, always get that undersore in there
  3. What is the type? Return type is the lowest common ancestor of all returned cases
   */

  // pattern matching on sealed hierarchies

}
