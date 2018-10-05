package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x = 42 // vals are immutable
  println(x)

  val aString = "hello"
  val aBoolean = true
  val aChar = 'a'
  val anInt: Int = x
  val aShort: Short = 2
  val aLong: Long = 123121321
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  //Variables
  var aVariable: Int = 4
  aVariable = 5 // vars can be reassigned, used for side effects. Programs without sideeffects are better in scala



}
