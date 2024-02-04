package lectures.part3fp

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple = (2, "hello Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)
  println(aTuple._2)

  println(aTuple.copy(_2 = "goodbye"))
  println(aTuple.swap) // ("Hello Scala" , 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()
  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)

  // a -> b is sugar for (a, b)
  println(phonebook)

  // maps ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, faltMaps, filter

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterkeys
  println(phonebook.view.filterKeys(_.startsWith("Jim")).toMap)

  // mapValues
  println(phonebook.view.mapValues(n => "+351" + n * 10).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

}
