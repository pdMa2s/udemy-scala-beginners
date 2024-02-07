package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "The one"
    case 2 => "double or nothing"
    case _ => "something else" // _ = wildcard
  }

  println(x)
  println(description)

  // 1. decompose values

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"

    println(greeting)

    /*
    1. case are matched in order
    2. if does not match? MatchError
    3. return type is the Unification of all the cases. for example, String + int => Any
    4. PM works really well with case classes
    * */

    // PM on sealed hierarchies
    sealed class Animal
    case class Dog(breed: String) extends Animal
    case class Parrot(greeting: String) extends Animal

    val animal: Animal = Dog("Terra Nova")
  animal match
    case Dog(breed) => println(s"dog of some breed, $breed")


  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def toHumanString(expr: Expr): String = {
    expr match
      case Prod(e1, e2) => s"${e1 match
        case Sum(_, _) => s"(${toHumanString(e1)})"
        case _ => toHumanString(e1)
      } * ${e2 match
        case Sum(_, _) => s"(${toHumanString(e2)})"
        case _ => toHumanString(e2)
      }"
      case Sum(e1, e2) => s"${toHumanString(e1)} + ${toHumanString(e2)}"
      case Number(n) => s"$n"
      case _ => throw new IllegalArgumentException("Illegal expression")
  }

  val sumN = Sum(Number(2), Number(3))
  val prodN = Prod(Number(2), Number(3))

  println(toHumanString(sumN))
  println(toHumanString(prodN))

  println(toHumanString(Prod(Sum(Number(2), Number(3)), Sum(Number(2), Number(3)))))
  println(toHumanString(Sum(Prod(Number(2), Number(3)), Number(3))))



}
