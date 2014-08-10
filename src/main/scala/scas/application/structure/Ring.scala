package scas.application.structure

import Ring.OpsImpl

trait Ring[@specialized(Int, Long, Double) T] extends Monoid[T] with scas.structure.Ring[T] {
  override implicit def mkOps(lhs: T): Ring.Ops[T] = new OpsImpl(lhs)(this)
}

object Ring {
  trait ExtraImplicits extends Monoid.ExtraImplicits with scas.structure.Ring.ExtraImplicits {
    implicit def infixRingOps[T: Ring](lhs: T) = implicitly[Ring[T]].mkOps(lhs)
  }
  object Implicits extends ExtraImplicits

  trait Element[T <: Element[T]] extends Structure.Element[T] with scas.structure.Ring.Element[T] { this: T =>
    val factory: Ring[T]
  }
  trait Ops[T] extends Structure.Ops[T] with scas.structure.Ring.Ops[T]
  class OpsImpl[T: Ring](lhs: T) extends Ops[T]
}
