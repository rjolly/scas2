package scas.polynomial

import scas.structure.{Residue, Field}
import scas.Implicits.{infixUFDOps, infixPowerProductOps}
import AlgebraicNumber.Element

trait AlgebraicNumber[S[C, N] <: Polynomial.Element[S[C, N], C, N], T <: Element[S, T, C, N], C, @specialized(Int, Long) N] extends UnivariatePolynomial[T, C, N] with PolynomialWithSyzygy[S, T, C, N] with Residue[T] with Field[T] {
  val list: List[T]
  def mod = list(0)
  def reduce(x: T) = if (list.isEmpty) x else reduce(x, mod)
  def inverse(x: T) = {
    val w = monic(gcd(apply(x, 0), mod))
    assert (w.isOne)
    fromPolynomial(w.element(0))
  }
  override def reduce(x: T, y: T) = {
    if (x.isZero) zero
    else {
      val (s, a) = head(x)
      val (t, b) = head(y)
      if (!(t | s)) x else {
        reduce(super.minus(x, multiply(y, s / t, a / b)), y)
      }
    }
  }
  override def toString = ring.toString + "(" + mod.toString + ")"
  override def toMathML = <msub>{ring.toMathML}{mod.toMathML}</msub>
}

object AlgebraicNumber {
  trait Element[S[C, N] <: Polynomial.Element[S[C, N], C, N], T <: Element[S, T, C, N], C, N] extends PolynomialOverUFD.Element[T, C, N] with PolynomialWithSyzygy.Element[S, T, C, N] { this: T =>
    val factory: AlgebraicNumber[S, T, C, N]
  }
}
