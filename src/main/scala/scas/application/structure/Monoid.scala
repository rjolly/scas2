package scas.application.structure

import scas.BigInteger
import spire.macros.Ops
import Monoid.OpsImpl

trait Monoid[@specialized(Int, Long, Double) T] extends Structure[T] with scas.structure.Monoid[T] {
  override implicit def mkOps(lhs: T): Monoid.Ops[T] = new OpsImpl(lhs)(this)
}

object Monoid {
  trait ExtraImplicits extends SemiGroup.ExtraImplicits {
    implicit def infixMonoidOps[T: Monoid](lhs: T) = implicitly[Monoid[T]].mkOps(lhs)
  }
  object Implicits extends ExtraImplicits

  trait Element[T <: Element[T]] extends Structure.Element[T] with scas.structure.Monoid.Element[T] { this: T =>
    val factory: Monoid[T]
  }
  trait Ops[T] extends Structure.Ops[T] with scas.structure.Monoid.Ops[T]
  class OpsImpl[T: Monoid](lhs: T) extends Ops[T]
}
