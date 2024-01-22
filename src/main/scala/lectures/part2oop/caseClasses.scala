package lectures.part2oop

object caseClasses extends App {

  case class Person(name: String, age: Int)

  // 1. class params are fields

  val jim = Person("Jim", 23)

  println(jim)

  // equals and hashcode implemented
  val jim2 = Person("Jim", 23)

  println(jim == jim2)

  // CCs have handy copy method
  val jim3 = jim2.copy(age = 45)

  println(jim3)

  // case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // case classes are serializable
  // Akka

  // CCs have extractor patterns == CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and "
  }
}
