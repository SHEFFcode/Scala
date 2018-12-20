package lectures.part5patternmatching

object PatternMatchExercise extends App {
  val numbers = List(1, 2, 3)
  val numberMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings" // this looks like :List
    case listOfNumbers: List[Int] => "a list of numbers" // this also looks like :List
    case _ => ""
  }

  println(numberMatch) // returns list of strings, this is a JVM trick question, after type checking the JVM is oblivious to types

  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 //?!
  } yield 10 * x

  // generators are also based on pattern matching

  val tuples = List((1, 2), (3, 4))
  val filterTuples  = for {
    (first, second) <- tuples
  } yield first * second

  // for case classes :: operators, ...

  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // named pattern matching at work here
  println(b)

  val head :: tail = list // this is based on pattern matching
  println(head)
  println(tail)

  // big idea #4
  //partial function
  val mappedList = list map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal, this is the same as

  val mappedLis2 = list map { x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

}
