package scas.polynomial

import scas.structure.EuclidianDomain
import scas.Implicits.{infixUFDOps, infixPowerProductOps}
import PolynomialOverUFD.Element

trait UnivariatePolynomial[T <: Element[T, C, N], C, N] extends PolynomialOverField[T, C, N] with EuclidianDomain[T] {
  assert (length == 1)
  def norm(x: T) = java.math.BigInteger.valueOf(degree(x))
  def derivative(w: T) = map(w, (a, b) => (a / pp.generators(0), b * ring(pp.degree(a))))
  def gcd(x: T, y: T): T = if (y.isZero) x else gcd(y, primitivePart(reduce(x, y)))
}
