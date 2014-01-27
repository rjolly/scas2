package scas

package object application {
  type Poly = MultivariatePolynomial.Element[BigInteger, Int]
  type RF = RationalFunction.Element[MultivariatePolynomial.Element, BigInteger, Int]
}
