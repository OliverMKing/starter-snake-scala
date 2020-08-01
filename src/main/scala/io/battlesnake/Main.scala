package io.battlesnake

import io.finch._
import cats.effect.IO
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await

object Main extends App with Endpoint.Module[IO] {
  println("Starting server...")

  def index: Endpoint[IO, String] = get(pathEmpty) { Ok("Hello World!")}


  def service: Service[Request, Response] = Bootstrap
    .serve[Text.Plain](index)
    .toService


  // Start server
  Await.ready(Http.server.serve(":8080", service))
}
