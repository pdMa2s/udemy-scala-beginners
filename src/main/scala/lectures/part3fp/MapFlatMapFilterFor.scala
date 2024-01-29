package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1, 2, 4)

  println(list)
  println(list.head)

  println(list.map(_ + 1))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  val numbers = List(1 ,2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  val comb = (l: List[Char]) => (n: Int) => l.flatMap((s: Char) => List((n.toString, s)))

  println(numbers.flatMap(comb(chars)))

  val comb2 = numbers.flatMap(n => chars.map(c => c + " " + n))
  println(comb2)

  val comb3 = numbers.flatMap(n => chars.flatMap(ch => colors.map(c => n + " " + ch + " " + c)))
  println(comb3)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield n + " " + c + " " + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

}
