package scas.application.structure.ordered

trait Ring[@specialized(Int, Long, Double) T] extends AbelianGroup[T] with Monoid[T] with scas.application.structure.Ring[T] with scas.structure.ordered.Ring[T] {
  implicit def self: Ring[T]
}
