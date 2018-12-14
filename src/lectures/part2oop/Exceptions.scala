package lectures.part2oop

object Exceptions extends App {
  val x: String = null

  //  println(x.length)
  // ^^ this will crash with a null pointer exception

  /*
  1. Throwing and catching exceptions
   */

   //val weirdValue: String = throw new NullPointerException // this is a valid value. Nothing is valid for any other type
  // throwable classes extend the throwable class
  // Exception and error are derived from throwable class.

  /*
  2. Catching exceptions
   */

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) {
      throw new RuntimeException("No int for you")
    }
    42
  }

  val potentialFail = try {
    getInt(true)
  } catch {
    case _: RuntimeException => println("Caught a runtime exception!")
  } finally {
    // code that will get executed no matter what
    // finally does not influence the return type of this expression
    // use finally only for side effect
    println("finally")
  }

  /*
  3. How to define your own exceptions
   */

  class MyException extends Exception
  val exception = new MyException
  throw exception


}
