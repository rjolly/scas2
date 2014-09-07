package scas.structure

import scala.xml.Elem
import scas.MathObject
import spire.macros.Ops
import scas.Implicits.infixOps

trait Structure[@specialized(Int, Long, Double) T] extends Equiv[T] {
  implicit def self: Structure[T]
  def convert(x: T) = x
  def apply(l: Long): T
  def random(numbits: Int)(implicit rnd: java.util.Random): T
  def nequiv(x: T, y: T) = !equiv(x, y)
  def toCode(x: T, precedence: Int) = x.toString
  def toMathML(x: T): Elem
  def toMathML: Elem
}

object Structure {
  trait ExtraImplicits {
    implicit def infixOps[T: Structure](lhs: T): Ops[T] = new OpsImpl(lhs)
  }
  object Implicits extends ExtraImplicits

  trait Element[T <: Element[T]] extends MathObject { this: T =>
    val factory: Structure[T]
    def ><(that: T) = factory.equiv(this, that)
    def <>(that: T) = factory.nequiv(this, that)
    override def toString = toCode(0)
    def toCode(precedence: Int) = factory.toCode(this, precedence)
    def toMathML = factory.toMathML(this)
  }
  trait Ops[T] {
    def ><(rhs: T) = macro Ops.binop[T, Boolean]
    def <>(rhs: T) = macro Ops.binop[T, Boolean]
    def toCode(rhs: Int) = macro Ops.binop[Int, String]
    def toMathML() = macro Ops.unop[Elem]
  }
  class OpsImpl[T: Structure](lhs: T) extends Ops[T]
}
