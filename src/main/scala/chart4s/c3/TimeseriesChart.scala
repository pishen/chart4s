package chart4s.c3

import chart4s.Decimal
import play.api.libs.json._

case class TimeseriesChart[A, T](
  lines: Seq[(String, Seq[A])],
  timeValues: Seq[T]
)(implicit d: Decimal[A], at: AxisTime[T]) extends Chart {
  def json = Json.obj(
    "bindTo" -> "#chart",
    "data" -> Json.obj(
      "x" -> "x",
      "xFormat" -> at.pattern,
      "columns" -> JsArray(
        JsArray(JsString("x") +: timeValues.map(at.format).map(JsString.apply)) +: lines.map {
          case (label, line) => JsArray(JsString(label) +: line.map(d.toBigDecimal).map(JsNumber.apply))
        }
      )
    ),
    "axis" -> Json.obj(
      "x" -> Json.obj(
        "type" -> "timeseries",
        "tick" -> Json.obj(
          "format" -> at.pattern
        )
      )
    )
  )
}
