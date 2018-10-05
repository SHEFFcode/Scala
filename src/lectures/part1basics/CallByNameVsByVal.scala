package lectures.part1basics

object CallByNameVsByVal extends App {
  def calledByValue(x: Long): Unit = {
    println("By value " + x)
    println("By value " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("By value " + x)
    println("By value " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)
//  printFirst(infinite(), 34) // crashes
  printFirst(34, infinite()) // survives, because second param is never used
}
