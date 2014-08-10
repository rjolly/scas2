package scas.application.structure.ordered

trait Ring[@specialized(Int, Long, Double) T] extends Structure[T] with scas.application.structure.Ring[T]
