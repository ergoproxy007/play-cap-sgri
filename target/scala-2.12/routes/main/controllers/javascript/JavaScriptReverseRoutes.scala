// @GENERATOR:play-routes-compiler
// @SOURCE:D:/play-framework/git/play-cap-sgri/conf/routes
// @DATE:Thu Jan 24 00:57:05 COT 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def verboseIndex: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.verboseIndex",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "action"})
        }
      """
    )
  
    // @LINE:10
    def getHello: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getHello",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sayHello"})
        }
      """
    )
  
    // @LINE:9
    def sayHello: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.sayHello",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sayHello"})
        }
      """
    )
  
    // @LINE:7
    def hello: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.hello",
      """
        function(name0) {
        
          if (name0 == """ + implicitly[play.api.mvc.JavascriptLiteral[String]].to("World") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "hello"})
          }
        
          if (true) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "hello/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("name", name0))})
          }
        
        }
      """
    )
  
    // @LINE:13
    def concurrent: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.concurrent",
      """
        function(seconds0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "concurrent/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("seconds", seconds0))})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:11
    def allHello: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.allHello",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allHello"})
        }
      """
    )
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
