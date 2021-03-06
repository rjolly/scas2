package scas.polynomial

import scala.reflect.ClassTag
import scas.structure.Ring
import Module.Element

class Module[R <: Polynomial.Element[R, C, N], C, N](val dimension: Int, val name: Option[String], val ring: Polynomial[R, C, N])(implicit val m: ClassTag[Element[R]], val cm: ClassTag[R]) extends scas.module.Module[R] {
  def multiply(x: Element[R], m: Array[N], c: C) = x :* ring(m, c)
  def multiply(x: Element[R], c: C) = x :* ring(c)
}

object Module {
  def apply[R <: Polynomial.Element[R, C, N], C, N](name: String, dimension: Int, ring: Polynomial[R, C, N])(implicit m: ClassTag[Element[R]], cm: ClassTag[R]) = new Module(dimension, Some(name), ring)
  def apply[R <: Polynomial.Element[R, C, N], C, N](dimension: Int, ring: Polynomial[R, C, N])(implicit m: ClassTag[Element[R]], cm: ClassTag[R]) = new Module(dimension, None, ring)

  type Element[R] = scas.module.Module.Element[R]
}
