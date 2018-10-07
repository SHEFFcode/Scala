package lectures.part2oop

object OOPBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Peter")

  val author = new Writer("Tim", "Steel", 1989)
  val novel = new Novel("Great Expectations", 1969, author)

  println(author.age)
  println(author.fullName())
  println(novel.isWrittenby(author))

  val counter = new Counter(0)
  counter.inc
  counter.dec
  counter.inc(5)
  counter.dec(5)
  println(counter.count)
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

/*
  A novel and a writer class
  Writer: first name, last name and year of birth, and method fullName which is first name + surname
  Novel: name, year of release, author, authorAge method, isWrittenBy(author), copy(new year): Novel
 */

/*
  Counter class (int), has method currentCount, methodIncrement, methodDecrement, can also receive a param for amount
 */

class Writer(firstName: String, surName: String, val age: Int) {
  def fullName(): String = s"$firstName $surName"
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.age
  def isWrittenby(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int) {
  def inc = {
    println("Incrementing")
    new Counter(count + 1) // Immutability, instances are fixed and cannot be modified, very important in func prog
  }
  def dec = {
    println("Decrementing")
    new Counter(count - 1)
  }
  def inc(count: Int): Counter = {
    if (count <= 0) this
    else inc.inc(count - 1)
  }
  def dec(count: Int): Counter = {
    if (count <= 0) this
    else dec.dec(count - 1)
  }
}

