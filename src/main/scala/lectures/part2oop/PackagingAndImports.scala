package lectures.part2oop

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {
  val writer = new Writer("Daniel", "RockToJVM", 1995)

  // package object

  sayHello

  // imports
  val date = new java.util.Date
  val sqlDate = new SqlDate(123L)

  // default imports
  // java.lang - String, Object, Exception
  // scala Int, Nothing, Function
  // scala Predef - println, ??
}
