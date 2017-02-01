package chart4s.c3

import chart4s.Decimal
import play.api.libs.json._

case class BarChart[A](
  data: Seq[(String, Seq[A])],
  xValues: Seq[String]
)(implicit d: Decimal[A]) extends Chart {
  def json = Json.obj(
    "bindTo" -> "#chart",
    "data" -> Json.obj(
      "columns" -> JsArray(
        data.map {
          case (label, line) => JsArray(JsString(label) +: line.map(d.toBigDecimal).map(JsNumber.apply))
        }
      ),
      "type" -> "bar"
    ),
    "axis" -> Json.obj(
      "x" -> Json.obj(
        "type" -> "category",
        "categories" -> Json.toJson(xValues)
      )
    )
  )
}
