package scas.quotient

import scas.structure.{Quotient, Field}
import scas.polynomial.{PolynomialOverUFD, PolynomialOverField}
import scas.{Variable, MultivariatePolynomial, PowerProduct}
import RationalFunction.Element

trait RationalFunction[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N] extends Quotient[Element[R, C, N], R[C, N]] {
  val ring: PolynomialOverUFD[R[C, N], C, N]
  import ring.{ring => coef, variables}
  def apply(n: R[C, N], d: R[C, N]) = {
    val c = ring(coef.gcd(ring.content(n), ring.content(d)))
    new Element[R, C, N](n / c, d / c)(this)
  }
  def apply(value: C): Element[R, C, N] = apply(ring(value))
  def generator(n: Int) = apply(ring.generator(n))
  def generators = ring.generators.map(apply)
  def random(numbits: Int)(implicit rnd: java.util.Random) = zero
  override def toString = coef.toString + "(" + variables.mkString(", ") + ")"
  override def toMathML = <mrow>{coef.toMathML}<mfenced>{variables.map(_.toMathML)}</mfenced></mrow>
}

object RationalFunction {
  def apply[C](ring: Field[C], s: Variable*): RationalFunction[MultivariatePolynomial.Element, C, Int] = apply(MultivariatePolynomial(ring, PowerProduct(s: _*)))
  def apply[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](ring: PolynomialOverField[R[C, N], C, N]) = new RationalFunctionOverField(ring)
  def integral[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](ring: PolynomialOverUFD[R[C ,N], C, N]) = new RationalFunctionImpl(ring)

  class Element[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](val _1: R[C, N], val _2: R[C, N])(val factory: RationalFunction[R, C, N]) extends Quotient.Element[Element[R, C, N], R[C, N]] {
    def canEqual(that: Any) = true
  }
  object Element extends ExtraImplicits

  trait ExtraImplicits {
    implicit def coef2rationalFunction[D, R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](value: D)(implicit f: D => C, factory: RationalFunction[R, C, N]) = factory(value)
  }
}
