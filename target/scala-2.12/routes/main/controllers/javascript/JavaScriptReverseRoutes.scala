
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/saikrishna/Alpha/AA_ASSMNT/conf/routes
// @DATE:Fri Dec 01 02:44:54 IST 2017

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:12
package controllers.javascript {

  // @LINE:12
  class ReverseAAController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def getCitiesWeather: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AAController.getCitiesWeather",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "get/weather/cities"})
        }
      """
    )
  
    // @LINE:12
    def getTimeZone: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AAController.getTimeZone",
      """
        function(query0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "get/timezone" + _qS([(query0 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("query", query0))])})
        }
      """
    )
  
  }


}
