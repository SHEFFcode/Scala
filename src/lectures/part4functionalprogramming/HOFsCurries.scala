package lectures.part4functionalprogramming

object HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // Function that takes in another function is a Higher Order Function
  // Examples are map, flatMap, filter in List

  //Function that applies a function n times over a given value

  //nTimes(f, n, x)
  //nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n - 1, f(x))
  //nTimes(f, 3, x) = f(f(f(x)))
  //nTimes(

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x: Int) => x + 1 // basic incrementer function
  println(nTimes(plusOne, 10, 1)) // call plus one on value 1 10 times, so we should get 11

  //ntb(f, n) = x => f(f(f...(x))) instead of applying right away, you return a lambda
  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  //Curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  //add 3
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))

  // another way to do curried functions in scala
  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormat: Double => String = curriedFormatter("%4.2f") // you have to specify the type here, or won't compile
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
  1. Define a method toCurry (f: (Int, Int) => Int) = (Int => Int => Int)
  2. Define a method fromCurry (f: (Int => Int => Int)) = (f: (Int, Int) => Int)
  3. Define a method compose (f, g) => x => f(g(x))
      Then (f, g) => x => g(f(x))
   */

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = {
    x => y => f(x, y)
  }

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int = {
    (x , y) => f(x)(y)
  }

  def compose[A, B, T](f: A => B, g: T => A): T => B = {
    x => f(g(x))
  }

  def andThen[A, B, C](f: A => B, g: B => C): A => C = {
    x => g(f(x))
  }
}
