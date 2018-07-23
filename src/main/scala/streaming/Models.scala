package streaming

object Models {
  case class Session(sessionId:String, value:Double, endSignal:Option[String])
}
