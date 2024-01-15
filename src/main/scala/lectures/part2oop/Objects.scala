package lectures.part2oop

object Objects {
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person {
    val N_EYES = 2

    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person (val name: String) {
    // instance-level functionality
  }
  //COMPANIONS

  def main(args: Array[String]): Unit = {


    println(Person.N_EYES)
    println(Person.canFly)

    // scala object = SINGLETON INSTANCE
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john)

    val bobbie = Person(mary, john)

    // Scala Application = scala object with
    // def main(args: Array[String]): Unit
  }


}
