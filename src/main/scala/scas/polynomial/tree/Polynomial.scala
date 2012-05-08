package scas.polynomial.tree

import scala.collection.SortedMap
import scas.Variable
import scas.polynomial.ordering.Lexicographic
import scas.polynomial.{TreePolynomial, PowerProduct}
import scas.structure.Ring
import Polynomial.Element

class Polynomial[C, @specialized(Int, Long) N](val ring: Ring[C], val pp: PowerProduct[N])(implicit val cm: ClassManifest[Element[C, N]]) extends TreePolynomial[Element[C, N], C, N] {
  def apply(value: SortedMap[Array[N], C]) = new Element(value)(this)
}

object Polynomial {
  def apply[C](ring: Ring[C], s: Variable): Polynomial[C, Int] = apply(ring, PowerProduct(Array(s), Lexicographic[Int]))
  def apply[C](ring: Ring[C], s: Variable, ss: Variable*): Polynomial[C, Int] = apply(ring, PowerProduct(Array(s) ++ ss, Lexicographic[Int]))
  def apply[C, @specialized(Int, Long) N](ring: Ring[C], pp: PowerProduct[N]) = new Polynomial(ring, pp)

  class Element[C, @specialized(Int, Long) N](val value: SortedMap[Array[N], C])(val factory: Polynomial[C, N]) extends TreePolynomial.Element[Element[C, N], C, N]
  object Element extends ExtraImplicits

  trait ExtraImplicits {
    implicit def coef2polynomial[D, C, @specialized(Int, Long) N](value: D)(implicit f: D => C, factory: Polynomial[C, N]) = factory(value)
  }
}
