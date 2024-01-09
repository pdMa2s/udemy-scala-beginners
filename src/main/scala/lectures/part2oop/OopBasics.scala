package lectures.part2oop

object OopBasics extends App{
  val person = new Person("Pedro", 28)

  println(person.age)
  person.greet("john")
  person.greet()

  val author = new Writer("Fernando", "Pessoa", 1889)
  val novel = new Novel("As Máquinas", 1910, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))


  val counter = new Counter
  counter.increment.print
  counter.increment.increment.increment.print
  counter.increment(10).print
}

// constructor
class Person (name: String, val age: Int = 0) {
  // class parameters are not attributes

  // body

  val x = 2
  println(x)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructor
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
  def fullName: String = s"$firstName $lastName"
}

class Novel(name: String, yearOfRelease: Int, val author: Writer) {
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(yearOfRelease: Int): Novel = new Novel(name, yearOfRelease, this.author)
}

class Counter(val value: Int = 0) {
  def increment: Counter = {
    println("Incrementing")
    new Counter(this.value+1)
  } // immutability, VERY IMPORTANT in functional programming

  def decrement: Counter = {
    println("Decrementing")
    new Counter(this.value-1)
  }

  def increment(amount: Int): Counter = {
    if (amount <= 0 ) this
    else increment.increment(amount-1)
  }
  def decrement(amount: Int): Counter = {
    if (amount <= 0) this
    else decrement.decrement(amount-1)
  }

  def print = println(value)

}