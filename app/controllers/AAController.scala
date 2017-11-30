package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import play.api.libs.json._
import play.api.{Configuration, Logger}
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import akka.pattern.after
import models._
import models.formats._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by saikrishna on 30/11/17.
  */
class AAController @Inject()(
                              cc: ControllerComponents,
                              implicit val system: ActorSystem,
                              ws: WSClient,
                              //                              cache: CacheApi,
                              conf: Configuration,
                              implicit val lec: ExecutionContext
                            ) extends AbstractController(cc) {
  
  val apiKey = conf.get[String]("API_KEY")
  
  
  def getTimeZone(query: String) = Action.async {
    implicit request: Request[AnyContent] =>
      request.cookies.foreach(cookie => Logger.debug(s"request id ${request.id} cookie ${cookie.name}=${cookie.value}"))
      request.headers.toMap.foreach { case (key, value) => Logger.debug(s"request id ${request.id} header $key=$value") }
      val ip = request.remoteAddress
      var q = ip
      query match {
        case "" =>
        case _ =>
          q = query
      }
      val url = s"${conf.get[String](s"weather.api.timeZone.uri")}$apiKey&q=$query"
      ws.url(url).get()
        .map(_.body)
        .map(Json.parse)
        .map(json =>
          json
        )
      Future(Ok(s": $ip"))
  }
  
  
  def getCitiesWeather = Action.async(parse.json) {
    implicit request =>
      request.body.validate[List[CityAndDate]].fold(err => Future(Ok(Json.obj("code" -> 1, "error" -> JsError.toJson(err)))),
        citDat => {
          val res = citDat.map {
            x =>
              lazy val t = after(duration = 10.seconds, using = system.scheduler)(Future.successful(Json.obj("code" -> 44, "message" -> "timeout from firstCompleteOf")))
              Logger.info(s"request for weather calculation $x")
              Future firstCompletedOf Seq(callWeatherForecast(x), t)
          }
          Future.sequence(res).map(x => Ok(Json.obj("code" -> 0, "result" -> Json.toJson(x))))
        })
  }
  
  def callWeatherForecast(citDate: CityAndDate) = {
    
    val key = citDate.city + "AIRASIA" + citDate.travelDate
    //    Logger.info(s"cache ${cacheWeather.city_date}")
    val citDateR: Set[CityDateWeather] = cacheWeather.city_date.filter(x => x.cityDate == key)
    Logger.info(s"\n found in $citDateR with key $key")
    if (citDateR.nonEmpty) {
      Logger.info("from object list")
      Future(citDateR.head.weather)
    }
    else {
      val url = s"${conf.get[String](s"weather.api.localweather.uri")}$apiKey&q=${citDate.city}&date=${citDate.travelDate}"
      ws.url(url).get()
        .map(_.body)
        .map(Json.parse)
        .map { json =>
          val resp = Json.obj("current_condition" -> (json \ "data" \ "current_condition").as[JsValue],
            "weather" -> (json \ "data" \ "weather").as[JsArray].value
              .map(x =>
                Json.obj(
                  "date" -> (x \ "date").as[String],
                  "hourly" -> (x \ "hourly").as[JsArray].value
                  .map(a => Json.obj(
                    "time" -> (a \ "time").as[String],
                    "visibility" -> (a \ "visibility").as[String],
                    "windspeedMiles" -> (a \ "windspeedMiles").as[String],
                    "tempC" -> (a \ "tempC").as[String]
                  ))))
              )
              createCacheKeyValue(key, resp)
              resp
        }
    }
  }
  
  
  private def createCacheKeyValue(key: String, resp: JsObject) = {
    cacheWeather.city_date = cacheWeather.city_date.++(List(CityDateWeather(key, resp)))
    //    cache.setIfNotExists(key, resp)
  }
}
