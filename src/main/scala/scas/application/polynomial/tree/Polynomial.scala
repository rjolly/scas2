package scas.application.polynomial.tree

import scala.reflect.ClassTag
import scala.collection.SortedMap
import scas.polynomial.TreePolynomial
import scas.application.power.PowerProduct
import scas.application.structure.UniqueFactorizationDomain
import Polynomial.Element

class Polynomial[C, N](val ring: UniqueFactorizationDomain[C], var pp: PowerProduct[N])(implicit val cm: ClassTag[Element[C, N]]) extends TreePolynomial[Element[C, N], C, N] with scas.application.polynomial.Polynomial[Element[C, N], C, N] {
  val self = this
  def apply(value: SortedMap[Array[N], C]) = new Element(value)(this)
}

object Polynomial {
  def apply[C](ring: UniqueFactorizationDomain[C]) = new Polynomial(ring, PowerProduct())

  class Element[C, N](val value: SortedMap[Array[N], C])(val factory: Polynomial[C, N]) extends TreePolynomial.Element[Element[C, N], C, N] with scas.application.polynomial.Polynomial.Element[Element[C, N], C, N]
  object Element extends ExtraImplicits

  trait ExtraImplicits {
    implicit def coef2polynomial[D, C, N](value: D)(implicit f: D => C, factory: Polynomial[C, N]) = factory(value)
  }
}
