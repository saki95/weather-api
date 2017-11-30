
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/saikrishna/Alpha/AA_ASSMNT/conf/routes
// @DATE:Fri Dec 01 02:44:54 IST 2017

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:12
package controllers {

  // @LINE:12
  class ReverseAAController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def getCitiesWeather(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "get/weather/cities")
    }
  
    // @LINE:12
    def getTimeZone(query:String = ""): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "get/timezone" + play.core.routing.queryString(List(if(query == "") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("query", query)))))
    }
  
  }


}
