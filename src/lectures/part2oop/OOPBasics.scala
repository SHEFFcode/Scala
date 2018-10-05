package lectures.part2oop

object OOPBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Peter")
}

// Class parameters are NOT Fields. To make a param into a field is adding val
class Person(name: String, val age: Int) {
  // Class Body
  val x = 2 // this will become a field
  println(x + 3)

  //method
  def greet(name: String): Unit = {
    println(s"Hi my name is ${this.name}, and I am $age years old, and your name is $name") // this.name will refer to the name passed in consutrctor
    //if we have a parameter in the method name that mirrors the class field, the variable will refer to the parameter
  }

  // overloading
  def greet(): Unit = {
    println(s"Hi my name is $name")
  }

  // auxilary constructor
  def this(name: String) = this(name, 0) // implementation can only be another constructor, and nothing else.
}