package lectures.part4functionalprogramming

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)

  // list methods
  println(list.head)
  println(list.tail)

  // filter
  println(list.filter(_ % 2 == 0))

  // flat map
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  // print "a1", "a2", ... "d4"
  // iterating
  val combos = numbers.flatMap(n => chars.map(c => s"$n$c"))
  println(combos)

  //foreach
  list.foreach(println)

  //for comprehensions
  val forCombinations = for { // this is translated into the flatMap and map above by the compiler
    n <- numbers if n % 2 == 0 // filter for only even numbers, translated into a filter call
    c <- chars
  } yield s"$n$c"

  println(forCombinations)

  for {
    n <- numbers
  } println(n) // same as forEach(println)


  // synthax overload
  list map { x =>
    println(x * 2)
  }
}
