val pieces = Array.ofDim[String](9, 9)
val alphabet = Array[Char]('a','b','c','d','e','f','g','h','i')

pieces(0)(0) = "L "
pieces(0)(1) = "N "
pieces(0)(2) = "S "
pieces(0)(3) = "G "
pieces(0)(4) = "K "
pieces(0)(5) = "G "
pieces(0)(6) = "S "
pieces(0)(7) = "N "
pieces(0)(8) = "L "

pieces(1)(0) = "  "
pieces(1)(1) = "R "
pieces(1)(2) = "  "
pieces(1)(3) = "  "
pieces(1)(4) = "DE"
pieces(1)(5) = "  "
pieces(1)(6) = "  "
pieces(1)(7) = "B "
pieces(1)(8) = "  "

pieces(2)(0) = "P "
pieces(2)(1) = "P "
pieces(2)(2) = "P "
pieces(2)(3) = "P "
pieces(2)(4) = "P "
pieces(2)(5) = "P "
pieces(2)(6) = "P "
pieces(2)(7) = "P "
pieces(2)(8) = "P "

pieces(8)(0) = "L "
pieces(8)(1) = "N "
pieces(8)(2) = "S "
pieces(8)(3) = "G "
pieces(8)(4) = "K "
pieces(8)(5) = "G "
pieces(8)(6) = "S "
pieces(8)(7) = "N "
pieces(8)(8) = "L "

pieces(7)(0) = "  "
pieces(7)(1) = "R "
pieces(7)(2) = "  "
pieces(7)(3) = "  "
pieces(7)(4) = "DE"
pieces(7)(5) = "  "
pieces(7)(6) = "  "
pieces(7)(7) = "B "
pieces(7)(8) = "  "

pieces(6)(0) = "P "
pieces(6)(1) = "P "
pieces(6)(2) = "P "
pieces(6)(3) = "P "
pieces(6)(4) = "P "
pieces(6)(5) = "P "
pieces(6)(6) = "P "
pieces(6)(7) = "P "
pieces(6)(8) = "P "

for (
  i <- 3 to 5;
  j <- 0 to 8
) {
  pieces(i)(j) = "  "
}


var index = 0


println("   9    8    7    6    5    4    3    2    1 \n")
for (a <- 1 to 19) {
  if (a % 2 == 1) {
    for (b <- 1 to 48) print("-")
  } else {
    for (c <- 0 to 8) {
      print(" | " + pieces(index)(c))
    }
    print(" | \t" +alphabet(index))
    index += 1
  }
  println()
}