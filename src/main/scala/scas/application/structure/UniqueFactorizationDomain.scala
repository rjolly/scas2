package scas.application.structure

trait UniqueFactorizationDomain[@specialized(Int, Long, Double) T] extends Ring[T] with scas.structure.UniqueFactorizationDomain[T] {
  implicit def self: UniqueFactorizationDomain[T]
}

object UniqueFactorizationDomain {
  trait Element[T <: Element[T]] extends Ring.Element[T] with scas.structure.UniqueFactorizationDomain.Element[T] { this: T =>
    val factory: UniqueFactorizationDomain[T]
  }
}
