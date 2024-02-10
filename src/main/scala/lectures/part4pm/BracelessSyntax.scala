package lectures.part4pm

object BracelessSyntax extends App {
  // if - expressions

  val anyIfExpression = if (2 > 3 ) "bigger" else "smaller"

  val anIfExpression_V3 = if (2 > 3) "bigger" else "smaller"

  // scala3

  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  println(anIfExpression_v5)


  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for comprehension
  val aForComprehension = for {
    n <- List(1,2,3)
    s <- List("Black", "White")
  } yield s"$n,$s"

  // scala3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("Black", "White")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch_v2 = meaningOfLife match
    case 1 => "thie one"
    case 2 => "double or nothiong"
    case _ => "something else"

  // methods without braces

  def computeMeaningOfLife(arg: Int): Int =
    val partialResult = 40


    partialResult + 2
  end computeMeaningOfLife


  println(computeMeaningOfLife(76))

  // classes, traits and enums
  class Animal: // compiler expects the body of Animal
    def eat(): Unit =
      println("I'm eating")

    def grow(): Unit =
      println("I'm growing")

  end Animal
   val aSpecialAnimal = new Animal:
     override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs

}
