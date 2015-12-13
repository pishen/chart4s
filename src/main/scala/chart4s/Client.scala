package chart4s

import akka.actor._
import Client._
import play.api.libs.json._

class Client(out: ActorRef, hub: ActorRef) extends Actor {
  
  println("i'm in")
  
  hub ! Hub.AddClient(self)
  
  def receive = {
    case Send(msgType, json) =>
      out ! Json.stringify(Json.obj("msgType" -> msgType, "json" -> json))
  }
  
  override def postStop() = {
    hub ! Hub.RemoveClient(self)
  }
}

object Client {
  def props(out: ActorRef, hub: ActorRef) = Props(new Client(out, hub))
  case class Send(msgType: String, json: JsValue)
}
