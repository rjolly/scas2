package scas.application.structure

import scas.{MathObject, Variable}
import spire.macros.Ops
import Structure.OpsImpl

trait Structure[@specialized(Int, Long, Double) T] extends scas.structure.Structure[T] { outer =>
  def function(x: T, a: Variable): Double => Double
  implicit def mkOps(lhs: T): Structure.Ops[T] = new OpsImpl(lhs)(this)
  def render(value: T): MathObject = new MathObject {
    override def toString = toCode(value, 0)
    def toMathML = outer.toMathML(value)
  }
}

object Structure {
  trait ExtraImplicits {
    implicit def infixOps[T: Structure](lhs: T) = implicitly[Structure[T]].mkOps(lhs)
  }
  object Implicits extends ExtraImplicits

  trait Element[T <: Element[T]] extends scas.structure.Structure.Element[T] { this: T =>
    def function(that: Variable) = factory.function(this, that)
  }
  trait Ops[T] extends scas.structure.Structure.Ops[T] {
    def function(rhs: Variable) = macro Ops.binop[Variable, Double => Double]
  }
  class OpsImpl[T: Structure](lhs: T) extends Ops[T]
}
