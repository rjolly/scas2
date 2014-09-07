package scas.application.structure

import scas.Variable
import scas.Implicits.infixUFDOps
import scas.application.Implicits.{infixOps, R2R}

trait Quotient[T <: Product2[R, R], R] extends scas.structure.Quotient[T, R] with Field[T] { self =>
  implicit val ring: UniqueFactorizationDomain[R]
  def function(x: T, a: Variable) = {
    val self(n, d) = x
    n.function(a) / d.function(a)
  }
}

object Quotient {
  trait Element[T <: Element[T, R], R] extends scas.structure.Quotient.Element[T, R] with UniqueFactorizationDomain.Element[T] { this: T =>
    val factory: Quotient[T, R]
  }
}
