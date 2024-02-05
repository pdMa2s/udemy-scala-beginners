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
  val phonebook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 999).withDefaultValue(-1)

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

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)

    network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)

    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Jim"), "Mary")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(people)
  println(jimBob)
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    network.getOrElse(person, Set()).size

  println(nFriends(testNet, "Bob"))

  def personWithMostFriends(network: Map[String, Set[String]]): String = {
    def mostFriends(peopleSet: Set[String], mostPopular: String): String = {
      if (peopleSet.isEmpty) mostPopular
      else {
        val friendsMostPopular = nFriends(network, mostPopular)
        val friendsOther = nFriends(network, peopleSet.head)
        if (friendsMostPopular > friendsOther) mostFriends(peopleSet.tail, mostPopular)
        else mostFriends(peopleSet.tail, peopleSet.head)
      }
    }
    mostFriends(network.keySet.tail, network.keySet.head)
  }

  def mostFriends2(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(personWithMostFriends(testNet))
  println(mostFriends2(testNet))


  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testNet))


  def socialConnection(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(personB, Set(), network(personA) + personA)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}
