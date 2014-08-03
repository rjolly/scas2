package scas.polynomial.tree

import scala.reflect.ClassTag
import scas.power.PowerProduct
import scas.structure.UniqueFactorizationDomain
import PolynomialWithSugar.Element

class PolynomialWithFussy[C, N](val ring: UniqueFactorizationDomain[C], val pp: PowerProduct[N])(implicit val cm: ClassTag[Element[C, N]]) extends PolynomialWithSugar[C, N] with scas.gb.PolynomialWithFussy[Element[C, N], C, N]
