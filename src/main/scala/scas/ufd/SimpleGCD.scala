package scas.ufd

import scala.annotation.tailrec
import scas.polynomial.PolynomialOverUFD
import PolynomialOverUFD.Element

trait SimpleGCD[T <: Element[T, C, N], C, @specialized(Int, Long) N] { this: PolynomialOverUFD[T, C, N] =>
  @tailrec final def gcd1(x: T, y: T): T = if (y.isZero) x else gcd1(y, reduce(x, y))
}
