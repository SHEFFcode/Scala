package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // this is an expression, they evaluate to a value and have a type
  println(2 + 3 + 4)

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  //Instructions vs expressions

  //IF Expression
  val aCondition = true
  val aCondtionedValue = if (aCondition) 5 else 3 // if expression
  println(aCondtionedValue)

  // Loops are generally discauraged in Scala
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // NEVER WRITE THIS AGAIN ^
  // EVERYTHING IN SCALA IS AN EXPRESSION

  val aWeirdValue = (aVariable = 3) // type here is Unit which is analogous to void
  println(aWeirdValue) // Reassignments in scala are units, are turn ()

  // Examples of side effects: println, whiles, reassigning vars. these are still expressions returning unit

  //CODE BLOCKS
  //special types of expressions
  // value of the block is the value of its last expression
  // code blocks create a scope
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "good bye"
  }


}
