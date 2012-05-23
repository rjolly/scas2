package scas.base

import scas.polynomial.tree.AlgebraicNumber
import scas.{int2bigInteger, bigInteger2rational, pow}

object Complex extends AlgebraicNumber(1 + pow(i, 2)) {
  override def toString = "CC"
  override def toMathML = <complexes/>
}
