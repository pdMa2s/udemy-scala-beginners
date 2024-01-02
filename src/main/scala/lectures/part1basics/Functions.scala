package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello ", 42))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  // println(aParameterlessFunction) // Previous, it was possible to call a function without parameters

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("repeat", 3))

  // When in need loops, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int) = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n+1)
  }

  def greetings(name: String, age: Int): String = f"Hi, my name is $name, and I am $age years old"

  println(greetings("Philip", 3))

  def factorial(n: Int): Int = {
    if (n <= 1) n
    else n * factorial(n - 1)
  }

  println(factorial(5))

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    def primeUntil(n: Int, i: Int): Boolean = {
      if (n == i ) true
      else n % i != 0 && primeUntil(n, i+1)
    }
    if (n == 0 || n == 1) false
    else primeUntil(n, 2)
  }

  println(isPrime(37))
  println(isPrime(2))
  println(isPrime(6))

}
