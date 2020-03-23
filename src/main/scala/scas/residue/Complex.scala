package scas.residue

import scas.structure.StarRingWithUFD
import scas.{Variable, BigInteger, Rational, UnivariatePolynomial, int2bigInteger, bigInteger2rational}
import scas.Implicits.{ZZ, QQ, infixRingOps, coef2residue}

trait ComplexLike extends AlgebraicNumber[UnivariatePolynomial.Element[Rational, Int], Rational, Int] with StarRingWithUFD[Complex] {
  implicit val self = this
  implicit val ring = UnivariatePolynomial(QQ, Variable.sqrt(BigInteger(-1))(ZZ))
  import ring.pp
  update(1 + pow(sqrt(-1), 2))
  def sqrt(x: Complex) = { assert (x >< -1) ; generator(0) }
  def real(x: Complex) = coefficient(x, pp.one)
  def imag(x: Complex) = coefficient(x, pp.generator(0))
  def isReal(x: Complex) = apply(imag(x)) >< zero
  def isImag(x: Complex) = apply(real(x)) >< zero
  def conjugate(x: Complex) = apply(real(x)) - sqrt(-1) * apply(imag(x))
  override def toString = "CC"
  override def toMathML = <complexes/>
}
