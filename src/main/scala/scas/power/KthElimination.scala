package scas.power

import scala.reflect.ClassTag
import scas.Variable
import Ordering.Implicits.infixOrderingOps
import PowerProduct.{kthElimination, degreeReverseLexicographic}

class KthElimination[@specialized(Int, Long) N](val variables: Array[Variable], val k: Int)(implicit val nm: Numeric[N], val m: ClassTag[N], val cm: ClassTag[Array[N]]) extends PowerProduct[N] {
  val ordering = degreeReverseLexicographic[N](variables)
  def self(variables: Array[Variable]) = kthElimination[N](variables, k)
  def compare(x: Array[N], y: Array[N]): Int = {
    val n = length
    var i = n - 1
    while (i > n - k && (x(i) equiv y(i))) i -= 1
    if (x(i) < y(i)) -1
    else if (x(i) > y(i)) 1
    else ordering.compare(x, y)
  }
}
