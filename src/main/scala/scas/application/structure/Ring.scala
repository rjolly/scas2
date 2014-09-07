package scas.application.structure

trait Ring[@specialized(Int, Long, Double) T] extends AbelianGroup[T] with Monoid[T] with scas.structure.Ring[T]

object Ring {
  trait Element[T <: Element[T]] extends AbelianGroup.Element[T] with Monoid.Element[T] with scas.structure.Ring.Element[T] { this: T =>
    val factory: Ring[T]
  }
}
