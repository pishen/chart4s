package chart4s

trait Decimal[A] {
  def toBigDecimal(x: A): BigDecimal
}
