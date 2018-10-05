package lectures.part1basics

object DefaultArgs extends App {
  def trFactorial(n: Int, accum: Int = 1): Int = {
    if (n <= 1) accum
    else trFactorial(n - 1, n * accum)
  }

  val tenFact = trFactorial(10)


  def savePicature(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = {
    println("Saving picture")
  }

  savePicature(width = 100, height = 200) // we can clarify the names of the arguments for the compiler
}
