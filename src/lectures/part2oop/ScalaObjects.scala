package lectures.part2oop

object ScalaObjects extends App {
  // Scala does not have class level functionality ("static")
  // To emulate that functionality we can use objects
  object Person { // I define its type and it's only instance
    val N_EYES = 2
    def canFly: Boolean = false
    //This is called a factory method.  It build persons given some parameters
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  // We often write both objects and classes with the same name in the same scope
  // This separates instance level functionality from the class level (static) functionality
  // This approach is called companions
  class Person(val name: String) {

  }

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala objects are singleton instances
  val mary = new Person("mary") // I am assigning the one and only instance of person to mary
  var john = new Person("John")
  println(mary == john)

  val steve = Person
  val katie = Person
  println(steve == katie)

  val bobby = Person(mary, john) // because we are using apply on the Person object we are able to call it like this
  println(bobby.name)

  // Scala applications
  // Scala application is a scala object with a very particalar method: def main (args: Array[String])): Unit
}
