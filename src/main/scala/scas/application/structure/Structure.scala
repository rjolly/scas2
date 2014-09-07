package scas.application.structure

import scas.{MathObject, Variable}
import spire.macros.Ops
import scas.application.Implicits.infixOps

trait Structure[@specialized(Int, Long, Double) T] extends scas.structure.Structure[T] {
  implicit def self: Structure[T]
  def function(x: T, a: Variable): Double => Double
  def render(value: T): MathObject = new MathObject {
    override def toString = value.toCode(0)
    def toMathML = value.toMathML
  }
}

object Structure {
  trait ExtraImplicits {
    implicit def infixOps[T: Structure](lhs: T) = new OpsImpl(lhs)
  }
  object Implicits extends ExtraImplicits

  trait Element[T <: Element[T]] extends scas.structure.Structure.Element[T] { this: T =>
    val factory: Structure[T]
    def function(that: Variable) = factory.function(this, that)
  }
  trait Ops[T] extends scas.structure.Structure.Ops[T] {
    def function(rhs: Variable) = macro Ops.binop[Variable, Double => Double]
  }
  class OpsImpl[T: Structure](lhs: T) extends Ops[T]
}
