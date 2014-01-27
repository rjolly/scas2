package scas.quotient

import scas.polynomial.PolynomialOverUFD

class RationalFunctionImpl[R[C, N] <: PolynomialOverUFD.Element[R[C, N], C, N], C, N](val ring: PolynomialOverUFD[R[C, N], C, N]) extends RationalFunction[R, C, N]
