package scas.application.polynomial

import scas.polynomial.ufd.PolynomialOverUFD
import scas.polynomial.ufd.gb.PolynomialWithGB
import scas.application.power.PowerProduct
import scas.application.structure.UniqueFactorizationDomain
import scas.Variable
import scas.application.Function
import scas.application.Implicits.{infixRingOps, R2R}
import Polynomial.Element

trait Polynomial[T <: Element[T, C, N], C, N] extends PolynomialWithGB[T] with UniqueFactorizationDomain[T] {
  var pp: PowerProduct[N]
  def +=(variable: Variable): Unit = pp += variable
  def function(x: T, c: Variable) = (Function.zero /: iterator(x)) { (l, r) =>
    val (a, b) = r
    l + a.function(c) * b.function(c)
  }
}

object Polynomial {
  trait Element[T <: Element[T, C, N], C, N] extends PolynomialOverUFD.Element[T] with UniqueFactorizationDomain.Element[T] { this: T =>
    val factory: Polynomial[T, C, N]
  }
}
