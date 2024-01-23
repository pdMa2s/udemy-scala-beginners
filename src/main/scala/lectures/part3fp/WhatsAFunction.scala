package lectures.part3fp

object WhatsAFunction extends App {

  //DREAM: use functions as first class elements
  // problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") * 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function types Function2[A, B, R] === (A, B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

  val concatenator: (String, String) => String = (v1: String, v2: String) => v1 + v2

  println(concatenator("he", "llo"))

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))

  println(superAdder(3)(4)) // curried function

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
