package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  def unsafeMethod(): String = null

  // val result = Some(null) // WRONG
  val result = Option(unsafeMethod()) // Some or None

  println(result)

  // chained method
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  println(chainedResult)

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid Method")

  val betterChainedResult = betterUnsafeMethod().orElse(betterBackupMethod())
  println(betterChainedResult)

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p)
        .map(c => c.connect))).foreach(println)


  val connectionStatus2 = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection(h, p)
  } yield c.connect

  println("for comp " + connectionStatus2)

}
