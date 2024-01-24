package lectures.part3fp

object AnonymousFunctions extends App {
  // anonymous functions (LAMBDA)
  val doubler: Int => Int = x => x * 2

  // multiple parameters
  val adder = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething = () => 3

  println(justDoSomething) // lambdas must be called with parameters
  println(justDoSomething())

  // curly braces with lambadas
  val stringToInt = {(str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrement: Int => Int = _ + 1 // equivalent to x +> x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  val niceSuperAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  val nSAdder =  niceSuperAdder(3)
  println(nSAdder(4))

}
