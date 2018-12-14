package lectures.part2oop

object CaseClasses extends App {
  /*
  * Useful for defining class, companion object and sensible defaults in one go.
  * */

  case class Person(name: String, age: Int)
  // 1. class params are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name) // name here is automatically a field
  // 2. Sensible toString()
  // println(instance) = println(instance.toString)
  println(jim.toString) // this would have been Person@kjhasdfkjhadl

  // 3. Equals and HashCode implemented out of the box
  val jim2 = new Person("Jim", 34) // new person that is same as old person
  println(jim == jim2) // returns true, if it was not a case, it would be false. Since the objects are diff

  // 4. Case classes have handy copy methods
  val jim3 = jim.copy(age = 45) // this is a valid case for copy method for a case class
  println(jim3)

  // 5. Case classes have companion objects
  val thePerson = Person // companion object has been auto created
  val mary = Person("mary", 23) // apply method here, in practice we don't use new for case classes, we use this method

  // 6. Case classes are serializable, you can send them between JVMs

  // 7. Case classes have extractor patterns, can be used in pattern matching

  /*
  There is also a case object
   */
  // 1. Case Objects have same features as case classes, but they do not get companion objects, cause they are already
  case object UnitedKingdom {
    def name: String = "The united kingdom of great britain and ireland."
  }



}
