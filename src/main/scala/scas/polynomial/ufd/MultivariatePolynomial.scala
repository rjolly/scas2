package scas.polynomial.ufd

import scas.power.splittable.PowerProduct
import scas.long2bigInteger
import scas.Implicits.{infixUFDOps, infixPowerProductOps}
import PolynomialOverUFD.Element

trait MultivariatePolynomial[T[C, N] <: Element[T[C, N], C, N], C, N] extends PolynomialOverUFD[T[C, N], C, N] {
  implicit val pp: PowerProduct[N]
  val location = variables.length - 1
  def split: MultivariatePolynomial[T, T[C, N], N]
  override def gcd(x: T[C, N], y: T[C, N]) = if (location > 0) {
    val s = split
    convertFrom(s.gcd(convertTo(x)(s), convertTo(y)(s)))(s)
  } else {
    val (a, p) = contentAndPrimitivePart(x)
    val (b, q) = contentAndPrimitivePart(y)
    multiply(primitivePart(gcd1(p, q)), ring.gcd(a, b))
  }
  def convertTo(x: T[C, N])(s: MultivariatePolynomial[T, T[C, N], N]): T[T[C, N], N] = (s.zero /: iterator(x)) { (l, r) =>
    val (m, c) = r
    val t = pp.projection(m, location)
    l + s(s.pp.converter(variables)(t), s.ring.convert(apply(m / t, c)))
  }
  def convertFrom(x: T[T[C, N], N])(s: MultivariatePolynomial[T, T[C, N], N]): T[C, N] = (zero /: s.iterator(x)) { (l, r) =>
    val (m, c) = r
    l + convert(c) * pp.converter(s.variables)(m)
  }
}
