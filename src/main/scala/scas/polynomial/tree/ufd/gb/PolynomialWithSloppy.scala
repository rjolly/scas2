package scas.polynomial.tree.ufd.gb

import scala.reflect.ClassTag
import scas.power.PowerProduct
import scas.structure.UniqueFactorizationDomain
import PolynomialWithSugar.Element

class PolynomialWithSloppy[C, N](val ring: UniqueFactorizationDomain[C], val pp: PowerProduct[N])(implicit val cm: ClassTag[Element[C, N]]) extends PolynomialWithSugar[C, N]
