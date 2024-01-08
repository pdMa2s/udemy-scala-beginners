package lectures.part1basics

object StringOps extends App {

  val str: String = "hello, I am learning Scala"


  println(str.charAt(2))

  println(str.substring(7, 11))

  println(str.split(" ").toList)

  println(str.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt

  println('a' +: aNumberString :+ 'z')

  println(str.reverse)

  println(str.take(2))

  // S-interpolators

  val name = "David"
  val age = 12
  val greetings = s"Hello, my name is $name and I am $age"
  val anotherGreeting = s"Hello, my name is $name and I am ${age + 1}"

  // F-interpolators

  val speed = 1.2f

  val myth = f"$name%s can eat $speed%2.2f burgers per minute" // f-interpolator can also check types

  println(myth)

  // raw-interpolator

  println(raw"This is a \n newline") // char is not escaped
  val escaped = "This is a \n newline"

  println(raw"$escaped") // injected variables do get escaped
}
