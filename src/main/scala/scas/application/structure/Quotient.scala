package scas.application.structure

import scas.Variable
import scas.application.Implicits.{infixUFDOps, R2R}

trait Quotient[T <: Product2[R, R], R] extends scas.structure.Quotient[T] { self =>
  def function(x: T, a: Variable) = {
    val self(n, d) = x
    n.function(a) / d.function(a)
  }
}

object Quotient {
  trait Element[T <: Element[T, R], R] extends Product2[R, R] with UniqueFactorizationDomain.Element[T] { this: T =>
    val factory: Quotient[T, R]
  }
}
