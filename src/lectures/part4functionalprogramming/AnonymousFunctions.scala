package lectures.part4functionalprogramming

object AnonymousFunctions extends App {
  //ANNONYMOUS FUNCTION or lambda
  val doubler = (x: Int) => x * 2

  //multiple params in a lambda
  val adder = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething = () => 3

  // carefull
  println(justDoSomething) // the function itself
  println(justDoSomething()) // the result of the function

  // with curly braces
  val stringToInt = {
    str: String => str.toInt
  }

  // MOAR synthactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

}
