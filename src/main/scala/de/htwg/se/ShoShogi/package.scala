package de.htwg.se.ShoShogi

object HelloWorld {
  def main(args: Array[String]) {

    println("Hello World")
    var testVar: String = "HelloWorldVar"

    var (a, b) = Pair(40, 42)
    var (c, d) = Pair("Test", 42)

    print(a + b + c + d)

    val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var i = 0

    val ret
    = for (
      i <- numList if i != 3; if i < 5
    ) yield i

    println(ret)

    println(square(5))

    printIt('C')

    printArray('b', 1, 2, 3, 4, 5, 6, 6)

    println("1:" + rekursiveFkt(8))

    println("2:" + anwenden(square, 5))

    println("3:" + fakultaet(1))

    println("4:" + anwenden(addI2(5), 5))
  }

  def square(a: Int): Int = {
    return a * a
  }

  def printIt(a: Char): Unit = {
    println(a)
  }

  def printArray(c: Char, ints: Int*) = {
    for (i <- ints) {
      println(i)
      println(c)
    }
  }

  def rekursiveFkt(fib: Int): Int = {
    if (fib == 0 || fib == 1) {
      return 1
    }
    return rekursiveFkt(fib - 1) + rekursiveFkt(fib - 2)
  }

  def anwenden(f: Int => Int, x: Int) = f(x)

  def fakultaet(n: Int): Int = {
    def fakAkk(n: Int, akku: Int): Int = {
      if (n != 0)
        return fakAkk(n - 1, n * akku)
      return 0
    }

    return fakAkk(n, 1)
  }

  def addI(a: Int)(b: Int) = a + b

  def addI2(a: Int) = (b: Int) => a + b
}
