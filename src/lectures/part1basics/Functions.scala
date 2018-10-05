package lectures.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  def parameterlessFunction(): Int = 42

  println(aFunction("Hello", 3))
  println(parameterlessFunction())

  def aRepeaterFunction(aString: String, int: Int): String = {
    if (int ==1) aString
    else aString + aRepeaterFunction(aString, int - 1)
  }

  println(aRepeaterFunction("Hello", 3))

  //WHEN U NEED LOOPS, USE RECURSION
}
