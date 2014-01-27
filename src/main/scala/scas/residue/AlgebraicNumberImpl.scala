package scas.residue

import scas.polynomial.{PolynomialOverUFD, UnivariatePolynomial}

class AlgebraicNumberImpl[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](val ring: UnivariatePolynomial[R[C, N], C, N]) extends AlgebraicNumber[R, C, N]
