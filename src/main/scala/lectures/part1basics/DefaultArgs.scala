package lectures.part1basics

object DefaultArgs extends App {

  def factorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else factorial(n-1, n*acc)

  val fact10 = factorial(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

  savePicture("jpg", 800, 600)
  savePicture("svc", height = 100)

}
