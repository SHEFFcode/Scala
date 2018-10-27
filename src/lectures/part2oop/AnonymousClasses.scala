package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal = new Animal {
    override def eat: Unit = println("Haahahaha")
  } // we passed in the implementation for abstract class, we made an anonymous class so to say.

  println(funnyAnimal.getClass())

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Hi my name is Jim, how can I help?")
  } // here we used an anonymous class that extends Person and overrides the sayHi method. This works whether classes are abstract or not.
}
