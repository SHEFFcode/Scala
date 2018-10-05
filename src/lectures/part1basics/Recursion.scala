package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n < 1) 1
    else {
      println("Computing factorial of " + n + " I first need a factorial of " + (n - 1))
      val result = n * factorial(n - 1) // this does not work because the value is not the last line
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x < 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // this works because of tail recursion optimization
    }
    factorialHelper(n, 1)
  }

  println(anotherFactorial(5000))

  @tailrec
  def concatTailRec(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatTailRec(aString, n - 1, aString + accumulator)
  }

  println(concatTailRec("Hello ", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeHelper(t - 1, n % t != 0 && isStillPrime)
    }
    isPrimeHelper(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))

  def fibonacci(n: Int): Int = {
    def fibHelper(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibHelper(i + 1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else fibHelper(2, 1, 1)
  }

  println(fibonacci(8))

}
