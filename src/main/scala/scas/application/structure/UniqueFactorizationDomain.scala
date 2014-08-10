package scas.application.structure

import spire.macros.Ops
import UniqueFactorizationDomain.OpsImpl

trait UniqueFactorizationDomain[@specialized(Int, Long, Double) T] extends Ring[T] with scas.structure.UniqueFactorizationDomain[T] {
  override implicit def mkOps(lhs: T): UniqueFactorizationDomain.Ops[T] = new OpsImpl(lhs)(this)
}

object UniqueFactorizationDomain {
  trait ExtraImplicits extends Ring.ExtraImplicits with scas.structure.UniqueFactorizationDomain.ExtraImplicits {
    implicit def infixUFDOps[T: UniqueFactorizationDomain](lhs: T) = implicitly[UniqueFactorizationDomain[T]].mkOps(lhs)
  }
  object Implicits extends ExtraImplicits

  trait Element[T <: Element[T]] extends Ring.Element[T] with scas.structure.UniqueFactorizationDomain[T] { this: T =>
    val factory: UniqueFactorizationDomain[T]
  }
  trait Ops[T] extends Ring.Ops[T] with scas.structure.UniqueFactorizationDomain.Ops[T]
  class OpsImpl[T: UniqueFactorizationDomain](lhs: T) extends Ops[T]
}
