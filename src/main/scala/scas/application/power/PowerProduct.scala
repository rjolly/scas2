package scas.application.power

import scala.reflect.ClassTag
import scas.{Variable, long2bigInteger}
import scas.application.Function
import scas.application.structure.ordered.Monoid
import scas.math.Numeric
import Function.identity

trait PowerProduct[@specialized(Byte, Short, Int, Long) N] extends scas.power.PowerProduct[N] with Monoid[Array[N]] {
  implicit def self: PowerProduct[N]
  import nm.toLong
  def +(variable: Variable) = instance(variables ++ Array(variable))
  def instance(variables: Array[Variable]): PowerProduct[N]
  def function(x: Array[N], a: Variable) = Function.pow(identity, if (variables.contains(a)) toLong(x(variables.indexOf(a))) else 0)
}

object PowerProduct {
  def apply(variables: Variable*) = lexicographic[Int](variables: _*)
  def lexicographic[@specialized(Byte, Short, Int, Long) N](variables: Variable*)(implicit nm: Numeric[N], m: ClassTag[N], cm: ClassTag[Array[N]]) = new Lexicographic[N](variables.toArray)
}
