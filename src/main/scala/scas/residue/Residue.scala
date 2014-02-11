package scas.residue

import scas.structure.Field
import scas.polynomial.PolynomialOverUFD
import scas.{Variable, UnivariatePolynomial}
import Residue.Element

trait Residue[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N] extends scas.structure.Residue[Element[R, C, N], R[C, N]] {
  val ring: PolynomialOverUFD[R[C, N], C, N]
  import ring.{ring => coef}
  var list = List.empty[R[C, N]]
  def generator(n: Int) = fromRing(ring.generator(n))
  def generators = ring.generators.map(fromRing)
  def apply(value: C): Element[R, C, N] = reduce(ring(value))
  def fromRing(value: R[C, N]) = new Element[R, C, N](value)(this)
  def reduce(value: R[C, N]) = fromRing(ring.remainder(value, list))
  def unapply(x: Element[R, C, N]) = Some(x.value)
  def characteristic = ring.characteristic
  override def toString = coef.toString + "(" + list.mkString(", ") + ")"
  def toMathML = <msub>{coef.toMathML}<mrow>{list.map(_.toMathML)}</mrow></msub>
}

object Residue {
  def apply[C](ring: Field[C], s: Variable): AlgebraicNumber[UnivariatePolynomial.Element, C, Int] = apply(UnivariatePolynomial(ring, s))
  def apply[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](ring: scas.polynomial.UnivariatePolynomial[R[C, N], C, N]) = new AlgebraicNumberImpl(ring)

  class Element[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](val value: R[C, N])(val factory: Residue[R, C, N]) extends scas.structure.Residue.Element[Element[R, C, N], R[C, N]]
  object Element extends ExtraImplicits

  trait ExtraImplicits {
    implicit def coef2residue[D, R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](value: D)(implicit f: D => C, factory: Residue[R, C, N]) = factory(value)
  }
}
