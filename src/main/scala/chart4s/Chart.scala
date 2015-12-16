package chart4s

import play.api.libs.json._

class Chart(json: JsValue) {
  def draw()(implicit chartServer: ChartServer) = chartServer.broadcast(json)
}
