package object chart4s {
  implicit val int2BigDecimal = new Decimal[Int] {
    def toBigDecimal(x: Int) = BigDecimal(x)
  }

  implicit val long2BigDecimal = new Decimal[Long] {
    def toBigDecimal(x: Long) = BigDecimal(x)
  }

  implicit val float2BigDecimal = new Decimal[Float] {
    def toBigDecimal(x: Float) = BigDecimal.decimal(x)
  }

  implicit val double2BigDecimal = new Decimal[Double] {
    def toBigDecimal(x: Double) = BigDecimal(x)
  }
}
