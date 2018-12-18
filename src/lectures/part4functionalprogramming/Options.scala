package lectures.part4functionalprogramming

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val anotherOption: Option[Int] = None

  // Unsafe APIs
  def someMethod: String = null
  val result = Some(someMethod) // don't do this because this still returns null, which breaks the point of options
  val saferResult = Option(someMethod) // this is safer to write

  println(result)
  println(saferResult)

  // chained methods
  def backupMethod: String = "A valid result"
  val chainedResult = Option(someMethod).orElse(Option(backupMethod)) // this gives up a better outcome
  println(chainedResult)

  // if you are designing unsafe APIs
  def betterUnsafeMethod: Option[String] = None
  def betterBackupMethod: Option[String] = Some("A Valid result")
  val betterChainedResult = betterUnsafeMethod.orElse(betterBackupMethod)

  //methods on options
  println(saferResult.isEmpty)
  println(myFirstOption.get) // this is unsafe, if the option wraps a null, it will throw a null pointer exception

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10)) // this will be a None, because u don't fit the rpedicate
  println(myFirstOption.flatMap(x => Option(x))) // this flattens the collection
  println(myFirstOption.map(x => Option(x))) // this does not flatten the collection

  // for comprehension
  val config: Map[String, String] = Map(
    "host" -> "192.168.0.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  // try to establish a connection, call connect
  val host = config.get("host")
  val port = config.get("port")
  /*
  // This is the imperative code
  if (h != null) {
    if (p != null) {
      return Connection.apply(h, p)
    }
  } else {
    return null
  }
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p))) // functional way of establishing a connection
  val connectionStatus = connection.map(_.connect)
  connectionStatus.foreach(println)

  // Another way to get this even faster, chained solution
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(_.connect))
    .foreach(println)

  // Solution with for comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect.foreach(println)

}
