package lectures.part2oop

import scala.collection.SeqView.Concat

object Generics extends App {

  class MyList[+A] {
    // use type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
    */
  }

  class myMap[key, values]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) ??? HARD QUESTION

  // 2. NO = INVARIANCE
  class InvarianceList[A]
  val invarianteAnimalList: InvarianceList[Animal] = new InvarianceList[Animal]

  // 3. CONTRAVARIANTE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  // generic type needs proper bounded type
//  class Car
//  val newCage = new Cage(new Car)

}
