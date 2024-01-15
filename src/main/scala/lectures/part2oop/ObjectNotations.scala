package lectures.part2oop

import scala.language.postfixOps

object ObjectNotations extends App {

  class Person(val name: String, val favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String) : Boolean = movie == favouriteMovie

    def + (person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def + (nickname: String) : Person = new Person(s"$name the $nickname", favouriteMovie)
    def unary_! : String = s"$name, what the heck!"
    def unary_+ : Person = new Person(name, favouriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"
    def learns(subject: String) : String = s"$name learns $subject"
    def learnsScala : String = learns("Scala")
    def apply(n: Int) :String = s"$name watched $favouriteMovie $n times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes( "Inception"))
  // inflix notation = operator notation. It only work with methods with only one parameter
  // (syntactic sugar)

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATOR ARE METHODS
  // Akka actors have ! ? methods

  // prefix notation (another form of syntactic sugar)
  val x = -1
  val y = 1.unary_-

  // unary_prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) //equivalent


  println((mary + "Rockstar")())
  println((+mary).age)

  println(mary learns "german")
  println(mary learnsScala)
  println(mary(3))
}
