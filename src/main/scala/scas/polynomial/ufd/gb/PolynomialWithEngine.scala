package scas.polynomial.ufd.gb

import scala.collection.SortedSet
import scala.collection.mutable.ArrayBuffer
import scala.math.Ordering
import scas.polynomial.ufd.PolynomialOverUFD
import scas.Implicits.{infixOrderingOps, infixRingOps, infixPowerProductOps}
import PolynomialOverUFD.Element

trait PolynomialWithEngine[T <: Element[T, C, N], C, N] extends PolynomialOverUFD[T, C, N] {
  def normalize(x: T) = primitivePart(x)
  def s_polynomial(x: T, y: T) = {
    val (m, a) = head(x)
    val (n, b) = head(y)
    val gcd = pp.gcd(m, n)
    val (m0, n0) = (m / gcd, n / gcd)
    reduce(x * n0, m0, a, y, b)
  }
  def gcd1(x: T, y: T) = gcd(x, y)
  def gcd(x: T, y: T) = {
    val (a, p) = contentAndPrimitivePart(x)
    val (b, q) = contentAndPrimitivePart(y)
    val list = gb(p, q)
    multiply(if (list.size == 1) list(0) else one, ring.gcd(a, b))
  }

  type P <: Pair

  class Pair(val i: Int, val j: Int) { this: P =>
    val m = headPowerProduct(i)
    val n = headPowerProduct(j)
    val scm = pp.scm(m, n)
    def key = (scm, j, i)
    def process: Unit = {
      if(!b_criterion) {
        val p = poly
        if (!p.isZero) update(p)
        npairs += 1
      }
      remove
    }
    def poly = {
      println(this)
      normalize(reduce(s_polynomial(polys(i), polys(j)), polys))
    }
    override def toString = "{" + i + ", " + j + "}, " + scm.toCode(0) + ", " + reduction
    def reduction = if (m < n) m | n else n | m
    def principal = if (m < n) j else i
    def coprime = pp.coprime(m, n)
    def b_criterion: Boolean = {
      for (k <- 0 until polys.size) {
        if ((headPowerProduct(k) | scm) && considered(i, k) && considered(j, k)) return true
      }
      return false
    }
    def remove: Unit = {
      pairs -= this
      if(reduction) removed(principal) = true
    }
    def add: Unit = {
      pairs += this
      if (coprime) remove
    }
  }

  def apply(i: Int, j: Int): P
  def sorted(i: Int, j: Int) = if (i > j) apply(j, i) else apply(i, j)
  def make(index: Int): Unit = for (i <- 0 until index) apply(i, index).add
  def considered(i: Int, j: Int) = !pairs.contains(sorted(i, j))

  implicit def ordering = Ordering by { pair: P => pair.key }

  var pairs = SortedSet.empty[P]
  val removed = new ArrayBuffer[Boolean]
  val polys = new ArrayBuffer[T]
  var npairs = 0
  var npolys = 0

  def headPowerProduct(i: Int): Array[N] = headPowerProduct(polys(i))

  def gb(xs: T*) = {
    update(xs)
    process
    reduce
    toSeq
  }

  def update(s: Seq[T]): Unit = {
    println(s)
    s.foreach { p =>
      if (!p.isZero) update(p)
    }
    npairs = 0
    npolys = 0
  }

  def update(poly: T): Unit = {
    polys += poly
    removed += false
    val index = polys.size - 1
    println("(" + headPowerProduct(index).toCode(0) + ", " + index + ")")
    make(index)
    npolys += 1
  }

  def process: Unit = {
    println("process")
    while(!pairs.isEmpty) pairs.head.process
  }

  def reduce: Unit = {
    println("reduce")
    for (i <- polys.size - 1 to 0 by -1 if removed(i)) {
      removed.remove(i)
      polys.remove(i)
    }
    for (i <- 0 until polys.size) {
      polys(i) = normalize(reduce(polys(i), polys, true))
      println("(" + headPowerProduct(i).toCode(0) + ")")
    }
  }

  def toSeq = {
    println("signature = (" + npairs + ", " + npolys + ", " + polys.size + ")")
    val a = polys.toArray
    polys.clear
    a.toSeq
  }
}
