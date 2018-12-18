package lectures.part4functionalprogramming

import scala.annotation.tailrec

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
  println(aPhoneBook.mapValues(number => number * 10)) // make sure that the resulting keys do not overlap, ie "Jim" vs "JIM" with lowercase will overlap

  //conversions to other collections
  println(aPhoneBook.toList) // all the pairings are included in the list
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
  1. What would happen if I had two original entries "Jim" -> 555 and "JIM" ->  9000
  !!! Carefull with mapping keys
  2. Overly simplified social network based on maps
    - Person = String
      - add a person to the network
      - remove a person from the network
      - friend (mutual)
      - unfriend (mutual)

      - number of friends of a given person
      - person with the most friends
      - how many people have no friends
      - is there a social connection between two people
   */

    // add a person to the network
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }
  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // friend list of person a
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // friend list of person a
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    // removes everyone from the network
    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network) // this is a map where the removed person is being unfriended
    unfriended - person // now from this unfriended map we remove the person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty,"Bob"), "Mary")
  println(network)

  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary

  val people = add(add(add(empty, "bob"), "mary"), "jim")
  val jimbob = friend(people, "bob", "jim")
  val testNet = friend(jimbob, "bob", "mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(testNet, "bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1 // compare folks by _2 which is the set of folks, and return _1 which is the name
  }

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
      network.count(_._2.isEmpty) // number of pairings where the number of friends is 0
  }

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a) // in case I have a connection from a to a
  }

  println(socialConnection(testNet, "mary", "jim"))
  println(socialConnection(network, "Mary", "Bob"))

}
