package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(f"factorial of $n, I need factorial of ${n-1}")
      val result = n * factorial(n-1)
      println(f"computed factorial of $n")
      result
    }

  // println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL recursion = recursive call as the last expression

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))

  // when you need loops, USE_TAIL_RECURSION

  def concat(s: String, n: Int): String = {
    @tailrec
    def partialConcat(x: Int, partial: String): String = {
      if (x <= 1) partial
      else partialConcat(x - 1, partial + s)
    }

    partialConcat(n, s)
  }

  println(concat("hello", 3))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def primeUntil(n: Int, i: Int): Boolean = {
      if (n == i) true
      else if (n % i == 0) false
      else primeUntil(n, i + 1)
    }

    if (n == 0 || n == 1) false
    else primeUntil(n, 2)
  }

  println(isPrime(3628803))

  def fibonacci(n: Int): Int = {
    def fiboHelper(x: Int, last: Int, nextToLast: Int): Int =
      if (x >= n) last
      else fiboHelper(x+1, last + nextToLast, last)

    if (n <= 2) 1
    fiboHelper(2, 1, 1)
  }

  println(fibonacci(8)) // 2 + 3 + 4 + 5 + 7 = 21
}
