/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.math

import language.implicitConversions
import Numeric.{Ops, OpsImpl}

/**
 * @since 2.8
 */
object Numeric {
  trait ExtraImplicits {
    /** These implicits create conversions from a value for which an implicit Numeric
     *  exists to the inner class which creates infix operations.  Once imported, you
     *  can write methods as follows:
     *  {{{
     *  def plus[T: Numeric](x: T, y: T) = x + y
     *  }}}
     */
    implicit def infixNumericOps[@specialized(Int, Long) T: Numeric](x: T) = implicitly[Numeric[T]].mkNumericOps(x)
  }
  object Implicits extends ExtraImplicits { }

  trait Ops[@specialized(Int, Long) T] {
    val lhs: T
    val factory: Numeric[T]
    def +(rhs: T) = factory.plus(lhs, rhs)
    def -(rhs: T) = factory.minus(lhs, rhs)
    def *(rhs: T) = factory.times(lhs, rhs)
    def unary_-() = factory.negate(lhs)
    def abs(): T = factory.abs(lhs)
    def signum(): Int = factory.signum(lhs)
    def toInt(): Int = factory.toInt(lhs)
    def toLong(): Long = factory.toLong(lhs)
    def toFloat(): Float = factory.toFloat(lhs)
    def toDouble(): Double = factory.toDouble(lhs)
  }

  class OpsImpl[@specialized(Int, Long) T](val lhs: T, val factory: Numeric[T]) extends Ops[T]

  trait IntIsIntegral extends Integral[Int] {
    def plus(x: Int, y: Int): Int = x + y
    def minus(x: Int, y: Int): Int = x - y
    def times(x: Int, y: Int): Int = x * y
    def quot(x: Int, y: Int): Int = x / y
    def rem(x: Int, y: Int): Int = x % y
    def negate(x: Int): Int = -x
    def fromInt(x: Int): Int = x
    def toInt(x: Int): Int = x
    def toLong(x: Int): Long = x
    def toFloat(x: Int): Float = x
    def toDouble(x: Int): Double = x
  }
  implicit object IntIsIntegral extends IntIsIntegral with Ordering.IntOrdering

  trait LongIsIntegral extends Integral[Long] {
    def plus(x: Long, y: Long): Long = x + y
    def minus(x: Long, y: Long): Long = x - y
    def times(x: Long, y: Long): Long = x * y
    def quot(x: Long, y: Long): Long = x / y
    def rem(x: Long, y: Long): Long = x % y
    def negate(x: Long): Long = -x
    def fromInt(x: Int): Long = x
    def toInt(x: Long): Int = x.toInt
    def toLong(x: Long): Long = x
    def toFloat(x: Long): Float = x
    def toDouble(x: Long): Double = x
  }
  implicit object LongIsIntegral extends LongIsIntegral with Ordering.LongOrdering
}

trait Numeric[@specialized(Int, Long) T] extends Ordering[T] { outer =>
  def plus(x: T, y: T): T
  def minus(x: T, y: T): T
  def times(x: T, y: T): T
  def negate(x: T): T
  def fromInt(x: Int): T
  def toInt(x: T): Int
  def toLong(x: T): Long
  def toFloat(x: T): Float
  def toDouble(x: T): Double

  def zero = fromInt(0)
  def one = fromInt(1)

  def abs(x: T): T = if (lt(x, zero)) negate(x) else x
  def signum(x: T): Int =
    if (lt(x, zero)) -1
    else if (gt(x, zero)) 1
    else 0
  implicit def mkNumericOps(lhs: T): Ops[T] = new OpsImpl(lhs, outer)
}
