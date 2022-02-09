// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

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

  
    // @LINE:8
    def tutorial: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tutorial")
    }
  
    // @LINE:10
    def query: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "query")
    }
  
    // @LINE:7
    def explore: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "explore")
    }
  
    // @LINE:12
    def postRes(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "postresults")
    }
  
    // @LINE:6
    def index: Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:15
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
