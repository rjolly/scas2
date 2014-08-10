package scas.power.growable

import scala.reflect.ClassTag
import scas.Variable
import scas.math.Numeric

trait PowerProduct[@specialized(Byte, Short, Int, Long) N] extends scas.power.PowerProduct[N] {
  def +(variable: Variable) = self(variables ++ Array(variable))
  def self(variables: Array[Variable]): PowerProduct[N]
}

object PowerProduct {
  def apply(variables: Variable*) = lexicographic[Int](variables: _*)
  def lexicographic[@specialized(Byte, Short, Int, Long) N](variables: Variable*)(implicit nm: Numeric[N], m: ClassTag[N], cm: ClassTag[Array[N]]) = new Lexicographic[N](variables.toArray)
}