println("Hello World")

1 + 2
3.toString


var x = 5
x = 6

val y = 7


val s: String = "Hallo World"


def f(x: Int): Int = x + 1

// wenn beim parameter val davor steht (nur bei class)
// dann kann man von ausen diesen Parameter holen
// Bei case class ist das bereits val
case class Person(name: String) {
  val age = 21

  override def toString = name
}


val p = Person("Mert Zeybek")

p.age

object Mert {
  val name = "Mert"
}
