package scas

import scas.application.structure.Structure

package object application {
  trait ExtraImplicits {
    implicit val R2R = Function
  }
  object Implicits extends ExtraImplicits with Structure.ExtraImplicits

  lazy val Function = base.Function

  type Poly = MultivariatePolynomial.Element[BigInteger, Int]
  type RF = RationalFunction.Element[Poly, BigInteger, Int]
}
