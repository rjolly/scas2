package scas.polynomial

import scala.annotation.tailrec
import scala.reflect.ClassTag
import scas.power.PowerProduct
import scas.structure.Ring
import scas.BigInteger
import scas.Implicits.{ZZ, infixRingOps, infixPowerProductOps, infixOrderingOps}
import Polynomial.Element

trait Polynomial[T <: Element[T, C, N], C, N] extends Ring[T] {
  implicit val ring: Ring[C]
  implicit def pp: PowerProduct[N]
  implicit val cm: ClassTag[T]
  override def zero = apply()
  def generator(n: Int) = fromPowerProduct(pp.generator(n))
  def generators = pp.generators.map(fromPowerProduct)
  def signum(x: T) = if (x.isZero) 0 else ring.signum(lastCoefficient(x))
  def characteristic = ring.characteristic
  override def convert(x: T) = sort(map(x, (s, a) => (pp.converter(x.factory.variables)(s), ring.convert(a))))
  def apply(l: Long) = apply(ring(l))
  def random(numbits: Int)(implicit rnd: java.util.Random) = zero
  def plus(x: T, y: T): T
  def minus(x: T, y: T) = subtract(x, pp.one, ring.one, y)
  def equiv(x: T, y: T): Boolean = {
    val it = iterator(y)
    for ((a, b) <- iterator(x)) {
      if (!it.hasNext) return false
      val (c, d) = it.next
      if (a <> c) return false
      else if (b <> d) return false
    }
    !it.hasNext
  }
  def isUnit(x: T) = if (degree(x) > 0 || x.isZero) false else headCoefficient(x).isUnit
  def times(x: T, y: T) = (zero /: iterator(y)) { (l, r) =>
    val (a, b) = r
    subtract(l, a, -b, x)
  }
  def times(x: T, m: Array[N]) = map(x, (s, a) => (s * m, a))
  override def pow(x: T, exp: BigInteger) = {
    if (size(x) == 0) {
      if (exp.isZero) one else zero
    } else if (size(x) == 1) {
      val (a, b) = head(x)
      multiply(one, pp.pow(a, exp), ring.pow(b, exp))
    } else super.pow(x, exp)
  }
  override def toCode(x: T, precedence: Int) = {
    var s = ring.zero.toCode(0)
    var n = 0
    var m = 0
    val p = if (size(x) == 1) precedence else 0
    for ((a, b) <- reverseIterator(x)) {
      val c = ring.abs(b)
      val g = ring.signum(b) < 0
      val (t, u) = {
        if (a.isOne) (c.toCode(p), 1)
        else if (c.isOne) (a.toCode(p), pp.size(a))
        else (c.toCode(1) + "*" + a.toCode(1), 1 + pp.size(a))
      }
      s = {
        if (n == 0) {
          if (g) "-" + t else t
        } else {
          if (g) s + "-" + t else s + "+" + t
        }
      }
      m = if (g) u + 1 else u
      n += 1
    }
    val fenced = {
      if (n == 0) false
      else if (n == 1) {
        if (m == 1) false
        else precedence > 1
      } else precedence > 0
    }
    if (fenced) "(" + s + ")" else s
  }
  override def toString = ring.toString + pp.toString
  def toMathML(x: T) = {
    var s = ring.zero.toMathML
    var n = 0
    for ((a, b) <- reverseIterator(x)) {
      val c = ring.abs(b)
      val g = ring.signum(b) < 0
      val t = {
        if (a.isOne) c.toMathML
        else if (c.isOne) a.toMathML
        else <apply><times/>{c.toMathML}{a.toMathML}</apply>
      }
      s = {
        if (n == 0) {
          if (g) <apply><minus/>{t}</apply> else t
        } else {
          if (g) <apply><minus/>{s}{t}</apply> else <apply><plus/>{s}{t}</apply>
        }
      }
      n += 1
    }
    s
  }
  def toMathML = <mrow>{ring.toMathML}{pp.toMathML}</mrow>
  def apply(value: C): T = if(value.isZero) zero else apply(pp.one, value)
  def fromPowerProduct(value: Array[N]) = apply(value, ring.one)
  def apply(m: Array[N], c: C): T = apply((m, c))
  def apply(s: (Array[N], C)*): T

  def iterator(x: T): Iterator[(Array[N], C)]

  def iterator(x: T, m: Array[N]): Iterator[(Array[N], C)] = iterator(x).dropWhile { r =>
    val (s, _) = r
    s > m
  }

  def reverseIterator(x: T) = toSeq(x).reverseIterator

  def toSeq(x: T) = iterator(x).toSeq

  def variables = pp.variables

  def size(x: T): Int

  def head(x: T): (Array[N], C)

  def headPowerProduct(x: T) = { val (a, _) = head(x) ; a }

  def headCoefficient(x: T) = { val (_, b) = head(x) ; b }

  def last(x: T): (Array[N], C)

  def lastCoefficient(x: T) = { val (_, b) = last(x) ; b }

  def coefficient(x: T, m: Array[N]) = {
    val it = iterator(x, m)
    if (it.hasNext) {
      val (s, a) = it.next
      if (s >< m) a else ring.zero
    } else ring.zero
  }

  def degree(x: T) = (0l /: iterator(x)) { (l, r) =>
    val (a, _) = r
    scala.math.max(l, pp.degree(a))
  }

  @tailrec final def reduce(x: T, ys: TraversableOnce[T]): T = {
    val it = iterator(x)
    if (it.hasNext) {
      val (s, a) = it.next
      ys.find(reducer(s)) match {
        case Some(y) => {
          val (t, b) = head(y)
          reduce(reduce(x, s / t, a, y, b), ys)
        }
        case None => x
      }
    } else x
  }

  def reducer(s: Array[N])(y: T) = {
    val (t, _) = head(y)
    t | s
  }

  def reduce(x: T, ys: TraversableOnce[T], tail: Boolean): T = {
    val it = iterator(x)
    if (it.hasNext) {
      val (s, a) = it.next
      if (tail) {
        if (it.hasNext) {
          val (s, a) = it.next
          reduce(x, s, ys)
        } else x
      } else reduce(x, s, ys)
    } else x
  }

  @tailrec final def reduce(x: T, m: Array[N], ys: TraversableOnce[T]): T = {
    val it = iterator(x, m)
    if (it.hasNext) {
      val (s, a) = it.next
      ys.find(reducer(s)) match {
        case Some(y) => {
          val (t, b) = head(y)
          reduce(reduce(x, s / t, a, y, b), m, ys)
        }
        case None => {
          if (it.hasNext) {
            val (s, a) = it.next
            reduce(x, s, ys)
          } else x
        }
      }
    } else x
  }

  def reduce(x: T, m: Array[N], a: C, y: T, b: C) = subtract(multiply(x, b), m, a, y)

  def subtract(x: T, m: Array[N], c: C, y: T) = x + multiply(y, m, -c)

  def multiply(x: T, m: Array[N], c: C) = map(x, (s, a) => (s * m, a * c))

  def multiply(x: T, c: C) = map(x, a => a * c)

  def map(x: T, f: C => C): T = map(x, (s, a) => (s, f(a)))

  def map(x: T, f: (Array[N], C) => (Array[N], C)): T

  def sort(x: T) = apply(toSeq(x).sortBy({ r =>
    val (s, _) = r
    s
  })(pp.reverse): _*)
}

object Polynomial {
  trait Element[T <: Element[T, C, N], C, N] extends Ring.Element[T] { this: T =>
    val factory: Polynomial[T, C, N]
    def *(that: Array[N]) = factory.times(this, that)
  }
}
