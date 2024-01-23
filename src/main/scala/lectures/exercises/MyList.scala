package lectures.exercises


abstract class MyList[+T] {

  def head: T
  def tail: MyList[T]
  def isEmpty: Boolean
  def add[K >: T](element: K): MyList[K]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  // Higher-order functions
  def map[B](transformer: Function1[T, B]): MyList[B]

  def filter(fun: T => Boolean): MyList[T]

  def flatMap[B](transformer: T => MyList[B]): MyList[B]

  def ++[B >: T](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[K >: Nothing](element: K): MyList[K] = new Cons(element, EmptyList)
  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList

  def filter(fun: Nothing => Boolean): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[K >: A](element: K): MyList[K] = new Cons(element, this)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def printElements: String =
    if (tail.isEmpty) head.toString
    else s"$head " + tail.printElements

  def filter(fun: A => Boolean): MyList[A] =
    if (fun(head)) Cons(head, tail.filter(fun))
    else tail.filter(fun)

  def map[B](transformer: A => B): MyList[B] =
    Cons(transformer(head), tail.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

}

object LisTest extends App {
  val list: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  val anotherList: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, EmptyList)))

  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", EmptyList))
  println(listOfStrings.toString)

  println(list.map(new Function1[Int, Int] {
    def apply(v1: Int): Int = v1 * 2
  }).toString)

  println(list.filter((obj: Int) => obj % 2 == 0).toString)

  println((list ++ anotherList).toString)

  println(list.flatMap((originalObj: Int) => new Cons(originalObj, new Cons(originalObj + 1, EmptyList))).toString)
  println(list == anotherList)
}