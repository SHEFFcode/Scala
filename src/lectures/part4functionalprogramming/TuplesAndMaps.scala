package lectures.part4functionalprogramming

object TuplesAndMaps extends App {
  //tuples - finite ordered kind of like lists
  val aTuple = Tuple2(2, "Hello scala") // Tuple2[Int, String] aka (Int, String)
  val anotherTuple = (2, "Hello scala")  // alt shorter synthax, can group at most 22 items, same to functions that go from 0 to 22

  println(aTuple._1)
  println(aTuple._2)

  println(aTuple.copy(_2 = "Good bye java!")) // this is a copy with a change of the typle 2 value
  println(aTuple.swap) // swaps els in place

  // Maps associate keys to values
  val aMap: Map[String, Int] = Map()
  val aPhoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) // ways to add key value pairs to a map
  println(aPhoneBook)

  // Basic map ops
  println(aPhoneBook.contains("Jim")) // returns a bool of whether jim is in this map
  println(aPhoneBook("Jim")) // this returns a value associated to the key
  println(aPhoneBook("Mary")) // trying with a key that does not exist , this will crash !!! or -1 if u use withDefaultValue above

  // add a pairing
  val newPairing = "Mary" -> 678
  val aNewPhoneBook = aPhoneBook + newPairing // this creates a new phone book, because maps are immutable

  println(aNewPhoneBook)

  // functions on maps:
  //map, flatMap and filter
  println(aNewPhoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  //filter keys
  println(aPhoneBook.filterKeys(x => x.startsWith("J")))

  // mapValues
  println(aPhoneBook.mapValues(number => number * 10))

  //conversions to other collections
  println(aPhoneBook.toList) // all the pairings are included in the list
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))


}
