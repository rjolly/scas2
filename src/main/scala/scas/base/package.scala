package scas

import scas._
import Implicits.QQ

package object base {
  implicit val cc = AlgebraicNumber(QQ, PowerProduct('I))
  val i = cc.generators(0)
}
