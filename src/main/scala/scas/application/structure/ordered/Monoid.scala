package scas.application.structure.ordered

trait Monoid[@specialized(Int, Long, Double) T] extends Structure[T] with scas.application.structure.Monoid[T] with scas.structure.ordered.Monoid[T]
