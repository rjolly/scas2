package scas.polynomial.ufd

import scas.structure.EuclidianDomain
import scas.BigInteger
import scas.Implicits.{infixUFDOps, infixPowerProductOps}
import PolynomialOverUFD.Element

trait UnivariatePolynomial[T <: Element[T, C, N], C, N] extends PolynomialOverField[T, C, N] with EuclidianDomain[T] {
  assert (variables.length == 1)
  def norm(x: T) = BigInteger(degree(x))
  def derivative(x: T) = map(x, (a, b) => (a / pp.generator(0), b * ring(pp.degree(a))))
  def gcd(x: T, y: T) = gcd1(x, y)
  def modInverse(x: T, mod: T): T
}
