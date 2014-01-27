package scas.quotient

import scas.polynomial.{PolynomialOverUFD, PolynomialOverField}
import RationalFunction.Element

class RationalFunctionOverField[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](val ring: PolynomialOverField[R[C, N], C, N]) extends RationalFunction[R, C, N] {
  override def apply(n: R[C, N], d: R[C, N]) = {
    val c = ring(ring.lastCoefficient(d))
    new Element[R, C, N](n / c, d / c)(this)
  }
}
