package scas.polynomial.ordering.int

object Lexicographic extends Ordering {
  def compare(x: Array[Int], y: Array[Int]): Int = {
    val n = x.length - 1
    var i = n - 1
    while (i > 0 && x(i) == y(i)) i -= 1
    if (x(i) < y(i)) -1
    else if (x(i) > y(i)) 1
    else 0
  }
}
