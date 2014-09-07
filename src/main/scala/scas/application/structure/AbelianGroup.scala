package scas.application.structure

trait AbelianGroup[@specialized(Int, Long, Double) T] extends Structure[T] with scas.structure.AbelianGroup[T] {
  implicit def self: AbelianGroup[T]
}

object AbelianGroup {
  trait Element[T <: Element[T]] extends Structure.Element[T] with scas.structure.AbelianGroup.Element[T] { this: T =>
    val factory: AbelianGroup[T]
  }
}
