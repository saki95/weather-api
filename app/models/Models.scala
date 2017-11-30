package models

import play.api.libs.json.{JsValue, Json, OFormat}

/**
  * Created by saikrishna on 1/12/17.
  */
case class CityAndDate(city: String, travelDate: String)


case class CityDateWeather(cityDate: String, weather: JsValue)


object formats {
  implicit val cityAndDateFmt: OFormat[CityAndDate] = Json.format[CityAndDate]
  implicit val city_date_weatherFmt = Json.format[CityDateWeather]
  
}