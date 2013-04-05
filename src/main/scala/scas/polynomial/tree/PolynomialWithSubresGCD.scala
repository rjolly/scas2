package scas.polynomial
package tree

import scala.reflect.ClassTag
import scas.ufd.SubresGCD
import scas.power.PowerProduct
import scas.structure.UniqueFactorizationDomain
import MultivariatePolynomial.Element

class PolynomialWithSubresGCD[C, @specialized(Int, Long) N](val ring: UniqueFactorizationDomain[C], val pp: PowerProduct[N])(implicit val cm: ClassTag[Element[C, N]]) extends MultivariatePolynomial[C, N] with SubresGCD[Element[C, N], C, N] {
  def split = MultivariatePolynomial(MultivariatePolynomial(ring, pp.take(location)), pp.drop(location))
}
