package lectures.part5patternmatching

import scala.collection.immutable.Stream.Empty

object AllThePatterns extends App {
  // 1. Constants
  val x: Any = "scala"
  val constants = x match {
    case 1 => "a number"
    case "scala" => "the scala"
    case true => "the truth"
    case AllThePatterns => "A literal object"
  }

  //2. Match anything
  //2.1 wildcard
  val matchAnything = x match {
    case _ => "I match all things"
  }

  //2.2 variable
  val matchVariable = x match {
    case something => s"I have found $something" // another way to match anything, and then use it
  }

  //3, Tuples
  val aTuple = (1, 2)
  val matchAtuple = aTuple match {
    case (1, 1) => "one one"
    case (something, 2) => s"I have found a tuple with $something"
  }
  val nestedTuple = (1, (2, 3))
  val nestedTuplematch = nestedTuple match {
    case (_, (2, v)) => s"found something with a $v"
  }

  //4. Case classes - constructor pattern
  val aList: List[Int] = List(1, 2, 3, 4)
  val matchedList = aList match {
    case List(1, _, _, _) => // extractor
    case List(1, _*) => //list of args pattern
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => //infix pattern
  }

  //5. type specifieds
  val unknown: Any = 2
  val unknownItem = unknown match {
    case list: List[Int] => // this is a list, explicit type specifier
  }

  //7 name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ List(_, _) => // name binding
  }

  //8 Multi patterns
//  val multiPattern = aList match {
//    case Empty | List(1, 2) => // compound or multi pattern
//  }


  println(matchedList)


}
