package lectures.exercises

abstract class MyList[+T] {

  def head: T
  def tail: MyList[T]
  def isEmpty: Boolean
  def add[K >: T](element: K): MyList[K]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[K >: Nothing](element: K): MyList[K] = new Cons(element, EmptyList)
  override def printElements: String = ""
}

class Cons[+T](h: T, t: MyList[T]) extends MyList[T] {
  def head: T = h
  def tail: MyList[T] = t
  def isEmpty: Boolean = false
  def add[K >: T](element: K): MyList[K] = new Cons(element, this)

  override def printElements: String =
    if (tail.isEmpty) head.toString
    else s"$head " + tail.printElements

}

object LisTest extends App {
  val list: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", EmptyList))
  println(listOfStrings.toString)
}