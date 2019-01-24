// @GENERATOR:play-routes-compiler
// @SOURCE:D:/play-framework/git/play-cap-sgri/conf/routes
// @DATE:Thu Jan 24 00:57:05 COT 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def verboseIndex(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "action")
    }
  
    // @LINE:10
    def getHello(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "sayHello")
    }
  
    // @LINE:9
    def sayHello(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "sayHello")
    }
  
    // @LINE:7
    def hello(name:String): Call = {
    
      (name: @unchecked) match {
      
        // @LINE:7
        case (name) if name == "World" =>
          implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("name", "World"))); _rrc
          Call("GET", _prefix + { _defaultPrefix } + "hello")
      
        // @LINE:8
        case (name)  =>
          
          Call("GET", _prefix + { _defaultPrefix } + "hello/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("name", name)))
      
      }
    
    }
  
    // @LINE:13
    def concurrent(seconds:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "concurrent/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("seconds", seconds)))
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:11
    def allHello(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "allHello")
    }
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
