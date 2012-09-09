package scas.polynomial.tree

import scala.reflect.ClassTag
import scas.polynomial.PowerProduct
import scas.structure.Ring
import Polynomial.Element

class WeylAlgebra[C, @specialized(Int, Long) N](val ring: Ring[C], val pp: PowerProduct[N])(implicit val cm: ClassTag[Element[C, N]]) extends Polynomial[C, N] with scas.polynomial.WeylAlgebra[Element[C, N], C, N]
