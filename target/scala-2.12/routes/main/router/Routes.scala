
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/saikrishna/Alpha/AA_ASSMNT/conf/routes
// @DATE:Fri Dec 01 02:44:54 IST 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:12
  AAController_0: controllers.AAController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:12
    AAController_0: controllers.AAController
  ) = this(errorHandler, AAController_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, AAController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """get/timezone""", """controllers.AAController.getTimeZone(query:String ?= "")"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """get/weather/cities""", """controllers.AAController.getCitiesWeather()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:12
  private[this] lazy val controllers_AAController_getTimeZone0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("get/timezone")))
  )
  private[this] lazy val controllers_AAController_getTimeZone0_invoker = createInvoker(
    AAController_0.getTimeZone(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AAController",
      "getTimeZone",
      Seq(classOf[String]),
      "GET",
      this.prefix + """get/timezone""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_AAController_getCitiesWeather1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("get/weather/cities")))
  )
  private[this] lazy val controllers_AAController_getCitiesWeather1_invoker = createInvoker(
    AAController_0.getCitiesWeather(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AAController",
      "getCitiesWeather",
      Nil,
      "POST",
      this.prefix + """get/weather/cities""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:12
    case controllers_AAController_getTimeZone0_route(params) =>
      call(params.fromQuery[String]("query", Some(""))) { (query) =>
        controllers_AAController_getTimeZone0_invoker.call(AAController_0.getTimeZone(query))
      }
  
    // @LINE:13
    case controllers_AAController_getCitiesWeather1_route(params) =>
      call { 
        controllers_AAController_getCitiesWeather1_invoker.call(AAController_0.getCitiesWeather())
      }
  }
}
