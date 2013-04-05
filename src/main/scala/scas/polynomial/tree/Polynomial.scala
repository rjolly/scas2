package scas.polynomial
package tree

import scala.collection.SortedMap
import scas.Variable
import scas.power.PowerProduct
import scas.structure.Ring
import Polynomial.Element

trait Polynomial[C, N] extends TreePolynomial[Element[C, N], C, N] {
  def apply(value: SortedMap[Array[N], C]) = new Element(value)(this)
}

object Polynomial {
  def apply[C](ring: Ring[C], variables: Variable*): Polynomial[C, Int] = apply(ring, PowerProduct(variables: _*))
  def apply[C, N](ring: Ring[C], pp: PowerProduct[N]) = new PolynomialImpl(ring, pp)
  def solvable[C](ring: Ring[C], variables: Variable*): Polynomial[C, Int] = solvable(ring, PowerProduct(variables: _*))
  def solvable[C, N](ring: Ring[C], pp: PowerProduct[N]) = new SolvablePolynomial(ring, pp)
  def weylAlgebra[C](ring: Ring[C], variables: Variable*): Polynomial[C, Int] = weylAlgebra(ring, PowerProduct(variables: _*))
  def weylAlgebra[C, N](ring: Ring[C], pp: PowerProduct[N]) = new WeylAlgebra(ring, pp)

  class Element[C, N](val value: SortedMap[Array[N], C])(val factory: Polynomial[C, N]) extends TreePolynomial.Element[Element[C, N], C, N]
  object Element extends ExtraImplicits

  trait ExtraImplicits {
    implicit def coef2polynomial[D, C, N](value: D)(implicit f: D => C, factory: Polynomial[C, N]) = factory(value)
  }
}
