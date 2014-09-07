package scas.application.structure.ordered

trait UniqueFactorizationDomain[@specialized(Int, Long, Double) T] extends Ring[T] with scas.application.structure.UniqueFactorizationDomain[T] with scas.structure.ordered.UniqueFactorizationDomain[T]
