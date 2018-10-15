package lectures.part2oop

object Inheritance extends App {
  class Animal {
    val creatureType = "wild"
    def eat=println("nom nom nom")
    protected def crunch = println("crunching")
  }

  class Cat extends Animal  {
    def catEat = {
      super.eat // will refer to eat on the parent class
      crunch
    }
  }// inheritance, all non private fields and methods

  val cat = new Cat()
  cat.eat
  cat.catEat // we can call this method, which will call the protected method, but cannot call protected method directly.

  //constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, id: String) extends Person(name, age) // note that we have to call the extended class with params

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = println("Overriden eat")
  }

  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type substitution or polymorphism
  val unkownAnimal: Animal = new Dog("Canine")
  unkownAnimal.eat // will use one from dog, not from animal

  //Super - used when u want to reference a method or a field on the parent class
  // preventing overrides:
  // 1) Final on member or class - prevents overrides (prevents method overrides or class extensions)
  // 2) Seal the class - you can extends the class in this file only, but not in other files

}
