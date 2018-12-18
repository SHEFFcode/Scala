package lectures.part4functionalprogramming

import scala.util.Random

object Sequences extends App {
  //Seq
  val aSequence = Seq(1, 2, 3, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  //Ranges
  val range: Seq[Int] = 1 to 10
  val anotherRange: Seq[Int] = 1 until 10
  range.foreach(println) // 1 ... 10
  anotherRange.foreach(println) // 1 ... 9

  (1 to 10).foreach(_ => println("Hello"))

  //Lists
  val aList = List(1, 2, 3, 4)
  val prePends = 42 :: aList
  val prePendUsingPlus = 42 +: aList // same as above
  val appendsUsingPlus = aList :+ 42 // same as above
  println(prePends)
  println(prePendUsingPlus)
  println(appendsUsingPlus)

  val fiveApples = List.fill(5)("Apple") // curried function that takes a number and a value and constructs a list
  println(fiveApples)
  println(aList.mkString("-")) // concats and adds the delimiter

  //arrays - these are mutable
  val numbers = Array(1, 2, 3, 4)
  val threeEls = Array.ofDim[Int](3) // allocates space for three els, without needs to supply these values
  threeEls.foreach(println) // values are inits with default vals, here 0 for int, for strings it would be null

  // mutation
  numbers(2) = 0  // synthactic sugar for numbers.update(2, 0) update is a special method in scala like apply
  println(numbers.mkString(" "))

  //arrays and seq
  val numberSeq: Seq[Int] = numbers // although numbers is an array, which is a wrapper over java arrs, conversion can be applied
  println(numberSeq) // this says that this is a sequence of wrapped array, which is a special sequence.

  //vactors - vectors are basically an immutable sequence, effectively constant read and write, fast element aappend repend
  // implemented as a fixed branched trie with a branch factor of 32
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // we are adding a random int at a random index in the collection
      System.nanoTime() - currentTime // the otheration took this many nanoseconds
    }

    times.sum * 1.0 / maxRuns // average amount of time for a collection to be updated
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // advantage of lists is that it keeps reference to tails
  // disadvantage of lists is that updating an element in the middle takes a long time
  println(getWriteTime(numbersList))
  // advantage of vector is depth of the tree is small
  // disadvantage of vector is that it needs to replace an entire 32 element chunk
  println(getWriteTime(numbersVector))

}
