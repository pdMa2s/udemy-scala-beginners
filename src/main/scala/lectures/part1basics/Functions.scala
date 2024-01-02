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

  
}
