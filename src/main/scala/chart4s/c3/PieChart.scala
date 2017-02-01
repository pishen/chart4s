package chart4s.c3

import chart4s.Decimal
import play.api.libs.json._

case class PieChart[A](pie: Seq[(String, A)])(implicit d: Decimal[A]) extends Chart {
  def json = Json.obj(
    "bindTo" -> "#chart",
    "data" -> Json.obj(
      "columns" -> JsArray(
        pie.map { case (label, value) =>
          Json.arr(label, d.toBigDecimal(value))
        }
      ),
      "type" -> "pie"
    )
  )
}
