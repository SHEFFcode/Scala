package lectures.part2oop

object Generics extends App {
  class Mylist[+A] {
    // Type A in there
    def add[B >: A](element: B): Mylist[B] = ???
    /*
    A = Cat
    B = Dog extends Animal
    now we have a list of Animals
     */
  }

  class MyMap[Key, Value] // two generic types

  val listOfIntegers = new Mylist[Int]
  val listOfStrings = new Mylist[String]

  // Generic methods
  object Mylist { // objects cannot be type parametarized
    def empty[A]: Mylist[A] = ??? // returns nothing
  }

  val emptyListOfIntegers = Mylist.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Does a list of cats extends a list of animals?
  // 1) Yes - List[Cat] extends List[Animal], this is called covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat] // covariant list
  // will adding a dog animalList.add(Dog) work? hard QQ to answer
  // 2) No - List[Cat] does not extend List[Animal] - invariance
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // cannot be a cat

  // 3) Sort of - contra variance, you can replace more specific thing to less specific
  class ContraVariantList[-A]
  val contraVariantList: ContraVariantList[Cat] = new ContraVariantList[Animal] // u can go in the direction of more generality

  // Bounded types
  class Cage[A <: Animal](animal: A) // class A can only be a subtype of Animal, upper bounded
  val cage: Cage[Animal] = new Cage[Animal](new Dog) // this works.

  class Car
//  val carCage = new Cage[Animal](new Car) cannot do this

}
