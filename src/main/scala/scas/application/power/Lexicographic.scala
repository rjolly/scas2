package scas.application.power

import scala.reflect.ClassTag
import scas.Variable
import scas.math.{Ordering, Numeric}
import Ordering.Implicits.infixOrderingOps
import PowerProduct.lexicographic

class Lexicographic[@specialized(Byte, Short, Int, Long) N](val variables: Array[Variable])(implicit val nm: Numeric[N], val m: ClassTag[N], val cm: ClassTag[Array[N]]) extends PowerProductWithDegree[N] {
  val self = this
  import variables.{length => n}
  def instance(variables: Array[Variable]) = lexicographic[N](variables: _*)
  def compare(x: Array[N], y: Array[N]): Int = {
    var i = n
    while (i > 0) {
      i -= 1
      if (get(x, i) < get(y, i)) return -1
      if (get(x, i) > get(y, i)) return 1
    }
    return 0
  }
}
