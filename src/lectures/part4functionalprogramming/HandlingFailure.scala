package lectures.part4functionalprogramming

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create success of failure
  val success = Success(3)
  val failure = Failure(new RuntimeException("Super Failure"))

  println(success)
  println(failure)

  // Construct try objects
  def unsafeMethod: String = throw new RuntimeException("I have failed")
  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  // Try in practice
  def anotherPotentialFailure = Try {
    // Some code that might fail
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod: String = "A valid result"
  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod))
  println(fallbackTry)

  // if you design an api
  def betterUnsafeMethod: Try[String] = Failure(new RuntimeException("I have hit a snag"))
  def betterBackupMethod: Try[String] = Success("Valid result")
  val betterFallback = betterUnsafeMethod orElse betterBackupMethod // better way to implement this API

  // map, flatMap, filter
  println(success.map(_ * 2))
  println(success.flatMap(x => Success(x * 10)))
  println(success.filter(_ > 10)) // this will turn success into a failure

  val hostName = "localhost"
  val port = "8080"
  def renderHtml(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "html body html"
      else throw new RuntimeException("Connection failed")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Service not available")
    }
  }

  // if you get an http page from connection, print it
  Try(HttpService.getConnection(hostName, port))
      .flatMap(connection => Try(connection.get("someUrl")))
    .foreach(println)

  // for comprehension
  for {
    connection <- Try(HttpService.getConnection(hostName, port))
    url <- Try(connection.get("someUrl"))
  } yield println(url)
}
