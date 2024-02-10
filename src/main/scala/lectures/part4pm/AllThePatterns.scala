package lectures.part4pm

import lectures.exercises.{Cons, Empty, EmptyList, MyList}

object AllThePatterns extends App {

  //1 - constants
  val x: Any = "scala"
  val constants = x match
    case 1 => " a number"
    case "scala" => "the scala"
    case true => "the Truth"
    case AllThePatterns => "A singleton object"

  // 2 - match anything
  // 2.1 - wildcard

  val matchAnything = x match
    case _ =>


  // 2.2 variable
  val matchAVariable = x match
    case something => s"I've found $something"


  // 3. tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match
    case (1, 1) =>
    case (something, 2) => s"I've found $something"


  val nestedTuple = (1, (2, 3))

  val matchNestedTuple = nestedTuple match
    case (_, (2, v)) =>

  // PMs can be nested!

  // 4 - case classes - constructor pattern

  // can be nested with case classes
  val aList: MyList[Int] = Cons(1, Cons(2, EmptyList))
  val matchAList = aList match
    case EmptyList =>
    case Cons(h, Cons(subHead, subTail)) =>


  // 5 - list patterns
  val aStandardList = List(1,2,3,32)
  val standardListMatching = aStandardList match
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // list of arbitrary length - advance
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 42 => // infix pattern

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match
    case list: List[Int] => // explicit type specifier
    case _ =>

  // 7 - name binding
  val nameBindingMatch = aList match
    case nonEmptyList @ Cons(_, _) => // name binding => use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns

    // 8 - multi-patterns
    val multipattern = aList match {
      case EmptyList | Cons(0, EmptyList) => // compound pattern (multi-pattern)
    }
  // 9 - if guards
  val secondElementSpecial = aList match
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>


  val numbers = List(1,2,3)
  val numbersMatch = numbers match
    case listOfStrings: List[String] => "A list of Strings"
    case listOfNumbers: List[Int] => "A list of numbers"
    case _ => "nothing"
  // THE JVM DOES IS NOT AWARE OF TYPE PARAMETERS
  println(numbersMatch)
  // JVM trick question
}
