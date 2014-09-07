package scas.structure

import scas.BigInteger
import scas.Implicits.infixRingOps
import Product.Element

class Product[R1, R2](implicit val ring1: Ring[R1], ring2: Ring[R2]) extends Ring[Element[R1, R2]] {
  val self = this
  override def convert(x: Element[R1, R2]) = {
    val Element(a, b) = x
    apply(ring1.convert(a), ring2.convert(b))
  }
  def apply(a: R1, b: R2) = new Element(a, b)(this)
  def apply(l: Long) = apply(ring1(l), ring2(l))
  def random(numbits: Int)(implicit rnd: java.util.Random) = {
    val a = ring1.random(numbits)
    val b = ring2.random(numbits)
    apply(if (rnd.nextBoolean()) -a else a, if (rnd.nextBoolean()) -b else b)
  }
  def isUnit(x: Element[R1, R2]) = {
    val Element(a, b) = x
    a.isUnit && b.isUnit
  }
  def signum(x: Element[R1, R2]) = {
    val Element(a, b) = x
    val l = ring1.signum(a)
    if (l == 0) ring2.signum(b) else l
  }
  def characteristic = BigInteger.lcm(ring1.characteristic, ring2.characteristic)
  def plus(x: Element[R1, R2], y: Element[R1, R2]) = {
    val Element(a, b) = x
    val Element(c, d) = y
    apply(a + c, b + d)
  }
  def minus(x: Element[R1, R2], y: Element[R1, R2]) = {
    val Element(a, b) = x
    val Element(c, d) = y
    apply(a - c, b - d)
  }
  def times(x: Element[R1, R2], y: Element[R1, R2]) = {
    val Element(a, b) = x
    val Element(c, d) = y
    apply(a * c, b * d)
  }
  def equiv(x: Element[R1, R2], y: Element[R1, R2]) = {
    val Element(a, b) = x
    val Element(c, d) = y
    a >< c && b >< d
  }
  override def toCode(x: Element[R1, R2], precedence: Int) = {
    val Element(a, b) = x
    "product(" + a.toCode(0) + ", " + b.toCode(0) + ")"
  }
  override def toString = ring1.toString + "*" + ring2.toString
  def toMathML(x: Element[R1, R2]) = {
    val Element(a, b) = x
    <apply><cartesianproduct/>{a.toMathML}{b.toMathML}</apply>
  }
  def toMathML = <apply><cartesianproduct/>{ring1.toMathML}{ring2.toMathML}</apply>
}

object Product {
  def apply[R1, R2](implicit ring1: Ring[R1], ring2: Ring[R2]) = new Product[R1, R2]

  case class Element[R1, R2](_1: R1, _2: R2)(val factory: Product[R1, R2]) extends Product2[R1, R2] with Ring.Element[Element[R1, R2]]
}
