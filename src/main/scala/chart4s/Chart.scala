package chart4s

import play.api.libs.json._

class Chart(json: JsObject) {
  def draw()(implicit chartServer: ChartServer) = {
    chartServer.broadcast(json + ("msgType" -> JsString("draw")))
  }
}
