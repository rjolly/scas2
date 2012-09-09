/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.math

import language.implicitConversions
import Integral.{Ops, OpsImpl}

/**
 * @since 2.8
 */
trait Integral[@specialized(Int, Long) T] extends Numeric[T] { outer =>
  def quot(x: T, y: T): T
  def rem(x: T, y: T): T
  override implicit def mkNumericOps(lhs: T): Ops[T] = new OpsImpl(lhs, outer)
}

object Integral {
  trait ExtraImplicits {
    /** The regrettable design of Numeric/Integral/Fractional has them all
     *  bumping into one another when searching for this implicit, so they
     *  are exiled into their own companions.
     */
    implicit def infixIntegralOps[@specialized(Int, Long) T: Integral](x: T) = implicitly[Integral[T]].mkNumericOps(x)
  }
  object Implicits extends ExtraImplicits

  trait Ops[@specialized(Int, Long) T] extends Numeric.Ops[T] {
    val factory: Integral[T]
    def /(rhs: T) = factory.quot(lhs, rhs)
    def %(rhs: T) = factory.rem(lhs, rhs)
    def /%(rhs: T) = (factory.quot(lhs, rhs), factory.rem(lhs, rhs))
  }

  class OpsImpl[@specialized(Int, Long) T](val lhs: T, val factory: Integral[T]) extends Ops[T]
}
