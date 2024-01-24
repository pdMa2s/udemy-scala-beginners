package lectures.part2oop

object Exceptions extends App {
  val x: String = null

  //println(x.length)

  //throwing and catching exceptions

  // val weirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtype

  // 2. How to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!!")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // no matter what
    // optional
    // does not influence the return type
    println("finally")
  }

  println(potentialFail)

  // 3.  define my exceptions
  class MyException extends OutOfMemoryError
  class stackOF extends StackOverflowError
  val exception = new MyException

  throw exception



}
