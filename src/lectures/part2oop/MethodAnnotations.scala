package lectures.part2oop

object MethodAnnotations extends App {
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangoutWith(otherPerson: Person) = s"$name is hanging out with ${otherPerson.name}"
    def unary_! : String = s"$name what the heck!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // ^ Infix notation or operator notation, only works with methods that only have on parameter
  // Inficx notation is an example of synthactic sugar

  /*
    Operators in Scala
   */
  val tom = new Person("Tom", "Fight Club")
  println(mary hangoutWith tom) // hangout with acts like an operator between two things, yielding a third thing
  println(1.+(2)) // all operators are methods

  // prefix notation
  val x = -1
  val y = 1.unary_- // these two are equivalent, only works with - + ~ !
  println(!mary)
  println(mary.unary_!) // these two are equivalent

  //postfix notation
  println(mary.isAlive)
  println(mary isAlive) // the two are equivalent, only available to methods without params

  // apply method name
  println(mary.apply())
  println(mary()) // these are equivalent, because apply is a special method
}
