package scas.application.structure

trait Monoid[@specialized(Int, Long, Double) T] extends Structure[T] with scas.structure.Monoid[T] {
  implicit def self: Monoid[T]
}

object Monoid {
  trait Element[T <: Element[T]] extends Structure.Element[T] with scas.structure.Monoid.Element[T] { this: T =>
    val factory: Monoid[T]
  }
}
