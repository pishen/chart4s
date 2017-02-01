package chart4s.c3

trait AxisTime[A] {
  def format(x: A): String
  def pattern: String
}
