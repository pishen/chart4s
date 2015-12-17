package chart4s

import akka.actor._
import play.api.BuiltInComponents
import play.api.http.ContentTypes._
import play.api.inject.{NewInstanceInjector, SimpleInjector}
import play.api.libs.json._
import play.api.mvc._
import play.api.routing.Router
import play.api.routing.sird._
import play.core.server._
import scala.io.Source

class ChartServer() extends NettyServerComponents with BuiltInComponents {
  implicit lazy val app = application
  private lazy val hub = actorSystem.actorOf(Props[Hub], "hub")

  private def getResource(path: String) = {
    Source.fromInputStream(getClass().getResourceAsStream(path)).mkString
  }

  lazy val router = Router.from {
    case GET(p"/") => Action {
      Results.Ok(getResource("/template.html")).as(HTML)
    }
    case GET(p"/assets/$file") => Action {
      val res = Results.Ok(getResource("/" + file))
      if (file.endsWith(".html")) {
        res.as(HTML)
      } else if (file.endsWith(".css")) {
        res.as(CSS)
      } else if (file.endsWith(".js")) {
        res.as(JAVASCRIPT)
      } else {
        res
      }
    }
    case GET(p"/socket") => WebSocket.acceptWithActor[String, String] { request => out => Client.props(out, hub) }
  }

  //BUG: https://groups.google.com/d/msg/play-framework/wHOH-MyEsfU/YneNmpL3wowJ
  override lazy val injector = new SimpleInjector(NewInstanceInjector) + router + crypto + httpConfiguration + actorSystem

  def broadcast(json: JsValue) = {
    hub ! Hub.Broadcast(Client.Send(json))
  }

  def stop() = server.stop

  //trigger the server
  server
}
