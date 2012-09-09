package scas.polynomial

import UnivariatePolynomialWithRepr.Element

trait UnivariatePolynomialWithRepr[S[C, N] <: Polynomial.Element[S[C, N], C, N], T <: Element[S, T, C, N], C, @specialized(Int, Long) N] extends UnivariatePolynomial[T, C, N] with PolynomialWithRepr[S, T, C, N] {
  def modInverse(x: T, mod: T) = {
    val w = gcd(apply(x, 0), mod)
    assert (w.isOne)
    fromPolynomial(w.element(0))
  }
}

object UnivariatePolynomialWithRepr {
  trait Element[S[C, N] <: Polynomial.Element[S[C, N], C, N], T <: Element[S, T, C, N], C, @specialized(Int, Long) N] extends PolynomialOverUFD.Element[T, C, N] with PolynomialWithRepr.Element[S, T, C, N] { this: T =>
    val factory: UnivariatePolynomialWithRepr[S, T, C, N]
  }
}
