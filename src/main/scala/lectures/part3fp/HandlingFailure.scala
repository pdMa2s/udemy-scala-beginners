package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("BO STRING FOR YOU")
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try{
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod().orElse(betterBackupMethod())
  println(fallbackTry)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // => for-comprehensions
  val hostname = "localhost"
  val port = 8080
  def renderHTML(page: String) = println(page)
  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: Int): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
  }

  for {
    conn <- Try(HttpService.getConnection(hostname, port))
    resp <- Try(conn.get("google.com")).orElse(Try("Failure to establish connection"))
  } yield renderHTML(resp)

}
