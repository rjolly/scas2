package scas.variable

import scala.xml.Elem
import scas.structure.Structure
import scas.Implicits.infixOps
import Variable.greek

trait Variable {
  def toMathML: Elem
  def mml(name: String) = greek.getOrElse(name, name)
}

object Variable {
  implicit def string2variable(s: String): Variable = apply(s)
  implicit def symbol2variable(s: Symbol): Variable = apply(s.name)

  def apply(name: String, subscript: Int*): Variable = new Constant(name, 0, subscript.toArray)
  def apply(name: String, prime: Int, subscript: Array[Int]): Variable = new Constant(name, prime, subscript)

  def function[T: Structure](name: String, parameter: Array[T]): Variable = new Function(name, (parameter.map(_.toCode(0)), parameter.map(_.toMathML)))
  def sqrt[T: Structure](x: T): Variable = new Sqrt(x.toCode(0), x.toMathML)

  val greek = Map(
    "Alpha"   -> "\u0391",
    "Beta"    -> "\u0392",
    "Gamma"   -> "\u0393",
    "Delta"   -> "\u0394",
    "Epsilon" -> "\u0395",
    "Zeta"    -> "\u0396",
    "Eta"     -> "\u0397",
    "Theta"   -> "\u0398",
    "Iota"    -> "\u0399",
    "Kappa"   -> "\u039A",
    "Lambda"  -> "\u039B",
    "Mu"      -> "\u039C",
    "Nu"      -> "\u039D",
    "Xi"      -> "\u039E",
    "Pi"      -> "\u03A0",
    "Rho"     -> "\u03A1",
    "Sigma"   -> "\u03A3",
    "Tau"     -> "\u03A4",
    "Upsilon" -> "\u03A5",
    "Phi"     -> "\u03A6",
    "Chi"     -> "\u03A7",
    "Psi"     -> "\u03A8",
    "Omega"   -> "\u03A9",
    "alpha"   -> "\u03B1",
    "beta"    -> "\u03B2",
    "gamma"   -> "\u03B3",
    "delta"   -> "\u03B4",
    "epsilon" -> "\u03B5",
    "zeta"    -> "\u03B6",
    "eta"     -> "\u03B7",
    "theta"   -> "\u03B8",
    "iota"    -> "\u03B9",
    "kappa"   -> "\u03BA",
    "lambda"  -> "\u03BB",
    "mu"      -> "\u03BC",
    "nu"      -> "\u03BD",
    "xi"      -> "\u03BE",
    "pi"      -> "\u03C0",
    "rho"     -> "\u03C1",
    "sigma"   -> "\u03C3",
    "tau"     -> "\u03C4",
    "upsilon" -> "\u03C5",
    "phi"     -> "\u03C6",
    "chi"     -> "\u03C7",
    "psi"     -> "\u03C8",
    "omega"   -> "\u03C9"
  )
}
