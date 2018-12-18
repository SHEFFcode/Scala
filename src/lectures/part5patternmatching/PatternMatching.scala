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
  4. Pattern matching works really well with case classes
   */

  // pattern matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greets: String) extends Animal

  val animal: Animal = Dog("stBernard")

  animal match {
    case Dog(someBreed) => println(s"A dog of $someBreed")
  }

  // match everything - this is overkill
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  /*
  Sum(Number(2), Number(3)) => "2 + 3"
  Sum(number(2), NUmber(3), Number(1))
  Product(Sum(Number(1),  NUmber(2)), Number(3)) => (2 + 1) * 3
  Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(el: Expr, el2: Expr) extends Expr
  case class Product(el: Expr, el2: Expr) extends Expr

  def makeHumanReadable(expr: Expr): String = {
    expr match {
      case Number(num) => s"$num"
      case Sum(a, b) => s"${makeHumanReadable(a)} + ${makeHumanReadable(b)}"
      case Product(a, b) => {
        def maybeShowParanthesis(e: Expr) = e match {
          case Product(_, _) => makeHumanReadable(e)
          case Number(_) => makeHumanReadable(e)
          case _ => s"(${makeHumanReadable(e)})"
        }
        s"${maybeShowParanthesis(a)} * ${maybeShowParanthesis(b)}"
      }
    }
  }

  println(makeHumanReadable(Sum(Number(2), Number(3))))
  println(makeHumanReadable(Sum(Sum(Number(2), Number(3)), Number(1))))
  println(makeHumanReadable(Product(Sum(Number(1),  Number(2)), Number(3))))
  println(makeHumanReadable(Sum(Product(Number(2), Number(1)), Number(3))))
}
