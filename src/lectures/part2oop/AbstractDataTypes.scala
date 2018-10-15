package lectures.part2oop

object AbstractDataTypes extends App {
  // abstract, leave some fields and method unimplemented
  abstract class Animal {
    val creatureType: String // abstract field
    def eat: Unit // abstract method
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Crunch crunch")
  }

  // Traits
  trait Carnivoure { // trait can only have abstract methods and fields
    def eat(animal: Animal): Unit // abstract method
  }

  trait ColdBloded

  class Crocodile extends Animal with Carnivoure with ColdBloded { // we can have as many traits as we want
    override val creatureType: String = "Crocodile"
    override def eat: Unit = println("I am devouring...")
    override def eat(animal: Animal): Unit = println(s"I'm a crock and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //traits vs abstract classes:
  // Abstract classes can have both abstract and non abstract methods and fields
  // Traits can only have abstract methods and fields
  // You can only inherit from one class, but multiple traits can be inherited by the same class
  // Traits are a type of behavior, but abstract class is a type of thing.
}
