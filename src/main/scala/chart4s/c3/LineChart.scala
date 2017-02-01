package chart4s.c3

import chart4s.Decimal
import play.api.libs.json._

case class LineChart[A, B](
  lines: Seq[(String, Seq[A])],
  xValues: Seq[B]
)(implicit da: Decimal[A], db: Decimal[B]) extends Chart {
  def json = Json.obj(
    "bindTo" -> "#chart",
    "data" -> Json.obj(
      "x" -> "x",
      "columns" -> JsArray(
        JsArray(JsString("x") +: xValues.map(db.toBigDecimal).map(JsNumber.apply)) +: lines.map {
          case (label, line) => JsArray(JsString(label) +: line.map(da.toBigDecimal).map(JsNumber.apply))
        }
      )
    )
  )
}
