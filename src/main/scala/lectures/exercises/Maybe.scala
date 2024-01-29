package lectures.exercises

import lectures.exercises

abstract class Maybe[+T] {
  def map[B](f: T => B): Maybe[B]
  def filter(f: T => Boolean): Maybe[T]
  def flatMap[B](f: T => Maybe[B]): Maybe[B]
}

case object Empty extends Maybe[Nothing] {
  override def map[B](f: Nothing => B): Maybe[B] = Empty

  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = Empty

  override def filter(f: Nothing => Boolean): Maybe[Nothing] = Empty
}

case class Just[+T](value: T) extends Maybe[T] {
  override def map[B](f: T => B): Maybe[B] = Just(f(value))

  override def filter(f: T => Boolean): Maybe[T] =
    if (f(value)) this
    else Empty

  override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)
}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}