package scas.application.structure.ordered

trait Structure[@specialized(Int, Long, Double) T] extends scas.application.structure.Structure[T] with scas.math.Ordering[T]
