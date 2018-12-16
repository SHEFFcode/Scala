package lectures.part4functionalprogramming

object WhatsAFunction extends App {
  // DREAM:  use and work with functions as first call elements
  // PROBLEM: We come from an OO world. JVM is designed for instances of classes

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("2") + 4)

  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
  1. Write a function that takes two strings and concats them
  2. Define a function that takes an int and returns another function that takens an int and returns an int
    - What is the type of this function?
    - How do you implement it
   */

  val concat = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  val superAdder: Function[Int, Int => Int]  = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  // Another way to write a super adder
  val superSuperAdder = (x: Int) => (y: Int) => x + y // curried function a lot shorter

  val adder3 = superAdder(3)(4) // curried function

}

trait MyFunction[A, B] {
  def apply(element: A): B
}