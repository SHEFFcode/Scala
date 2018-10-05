package lectures.part1basics

object Strings extends App {
  val aString = "Hello, I am learning scala."

  println(aString.charAt(2))
  println(aString.substring(7, 11))
  println(aString.split(" ").toList)
  println(aString.startsWith("Hello"))
  println(aString.replace(" ", "-"))
  println(aString.toLowerCase())
  println(aString.length())
  // Above are all the java functions that are available on a string
  // Below are scala specific functions for a string
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println(aNumber)
  println('a' +: aNumberString :+ '2') // prepending a, appending 2
  println(aNumberString.reverse)
  println(aNumberString.take(1))

  //Scala specific string interpolators
  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name, and I am $age years old, and I will be turning ${age + 1} years old" // s interpolated strings
  println(greeting)

  //F -interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // raw interpolators
  println(raw"This is a \n newline ") // not escaped
  val escaped = "This is a \n newline "
  println(raw"$escaped") // escaped

}
