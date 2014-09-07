package scas.application.structure.ordered

trait AbelianGroup[@specialized(Int, Long, Double) T] extends Structure[T] with scas.application.structure.AbelianGroup[T] with scas.structure.ordered.AbelianGroup[T] {
  implicit def self: AbelianGroup[T]
}
