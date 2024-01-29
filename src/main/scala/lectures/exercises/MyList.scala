package lectures.exercises

import lectures.exercises


abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[K >: A](element: K): MyList[K]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  // Higher-order functions
  def map[B](transformer: Function1[A, B]): MyList[B]

  def filter(fun: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit

  def sort(f: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], fun: (A, B) => C): MyList[C]

  def fold[B](start: B)(fun: (B, A) => B): B
}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[K >: Nothing](element: K): MyList[K] = Cons(element, EmptyList)
  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList

  def filter(fun: Nothing => Boolean): MyList[Nothing] = EmptyList

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList

  override def zipWith[B, C](list: MyList[B], fun: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists of different lengths")
    else EmptyList

  override def fold[B](start: B)(fun: (B, Nothing) => B): B = start
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

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]) : MyList[A] =
      if (sortedList.isEmpty) Cons(x, EmptyList)
      else if (f(x, sortedList.head) <= 0 ) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = tail.sort(f)
    insert(head, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], fun: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists of different lengths")
    else Cons(fun(head, list.head), tail.zipWith(list.tail, fun))
  }

  override def fold[B](start: B)(fun: (B, A) => B): B = {
    tail.fold(fun(start, head))(fun)
  }
}

object LisTest extends App {
  val list: MyList[Int] = Cons(1, Cons(2, Cons(3, EmptyList)))
  val anotherList: MyList[Int] = Cons(4, Cons(5, Cons(6, EmptyList)))

  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfStrings: MyList[String] = Cons("Again", Cons("Hello", Cons("Scala", EmptyList)))
  println(listOfStrings.toString)

  // println(list.map(x => x * 2).toString)
  println(list.map(_ * 2).toString)

  // println(list.filter(obj => obj % 2 == 0).toString)
  println(list.filter(_ % 2 == 0).toString)

  println((list ++ anotherList).toString)

  println(list.flatMap((originalObj: Int) => Cons(originalObj, Cons(originalObj + 1, EmptyList))).toString)
  println(list == anotherList)

  list.foreach(println)

  println(list.sort((x, y) => y - x))

  println(list.zipWith(listOfStrings, _ + ": " + _ ))

  println(list.fold(0)(_ + _))

  for {
    n <- list
    s <- listOfStrings
  } yield println(n + " " + s)

}