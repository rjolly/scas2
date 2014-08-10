package scas.application.structure

trait Field[@specialized(Int, Long, Double) T] extends UniqueFactorizationDomain[T] with scas.structure.Field[T]
