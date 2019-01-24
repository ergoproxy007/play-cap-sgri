// @GENERATOR:play-routes-compiler
// @SOURCE:D:/play-framework/git/play-cap-sgri/conf/routes
// @DATE:Thu Jan 24 00:57:05 COT 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:16
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:16
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_0, Assets_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hello""", """controllers.HomeController.hello(name:String = "World")"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hello/""" + "$" + """name<[^/]+>""", """controllers.HomeController.hello(name:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHello""", """controllers.HomeController.sayHello"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHello""", """controllers.HomeController.getHello"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """allHello""", """controllers.HomeController.allHello"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """action""", """controllers.HomeController.verboseIndex"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """concurrent/""" + "$" + """seconds<[^/]+>""", """controllers.HomeController.concurrent(seconds:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_HomeController_hello1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hello")))
  )
  private[this] lazy val controllers_HomeController_hello1_invoker = createInvoker(
    HomeController_0.hello(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "hello",
      Seq(classOf[String]),
      "GET",
      this.prefix + """hello""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_hello2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hello/"), DynamicPart("name", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_hello2_invoker = createInvoker(
    HomeController_0.hello(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "hello",
      Seq(classOf[String]),
      "GET",
      this.prefix + """hello/""" + "$" + """name<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_sayHello3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHello")))
  )
  private[this] lazy val controllers_HomeController_sayHello3_invoker = createInvoker(
    HomeController_0.sayHello,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "sayHello",
      Nil,
      "POST",
      this.prefix + """sayHello""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_getHello4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHello")))
  )
  private[this] lazy val controllers_HomeController_getHello4_invoker = createInvoker(
    HomeController_0.getHello,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getHello",
      Nil,
      "GET",
      this.prefix + """sayHello""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_allHello5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("allHello")))
  )
  private[this] lazy val controllers_HomeController_allHello5_invoker = createInvoker(
    HomeController_0.allHello,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "allHello",
      Nil,
      "GET",
      this.prefix + """allHello""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_HomeController_verboseIndex6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("action")))
  )
  private[this] lazy val controllers_HomeController_verboseIndex6_invoker = createInvoker(
    HomeController_0.verboseIndex,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "verboseIndex",
      Nil,
      "GET",
      this.prefix + """action""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_HomeController_concurrent7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("concurrent/"), DynamicPart("seconds", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_concurrent7_invoker = createInvoker(
    HomeController_0.concurrent(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "concurrent",
      Seq(classOf[Integer]),
      "GET",
      this.prefix + """concurrent/""" + "$" + """seconds<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_versioned8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned8_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:7
    case controllers_HomeController_hello1_route(params@_) =>
      call(Param[String]("name", Right("World"))) { (name) =>
        controllers_HomeController_hello1_invoker.call(HomeController_0.hello(name))
      }
  
    // @LINE:8
    case controllers_HomeController_hello2_route(params@_) =>
      call(params.fromPath[String]("name", None)) { (name) =>
        controllers_HomeController_hello2_invoker.call(HomeController_0.hello(name))
      }
  
    // @LINE:9
    case controllers_HomeController_sayHello3_route(params@_) =>
      call { 
        controllers_HomeController_sayHello3_invoker.call(HomeController_0.sayHello)
      }
  
    // @LINE:10
    case controllers_HomeController_getHello4_route(params@_) =>
      call { 
        controllers_HomeController_getHello4_invoker.call(HomeController_0.getHello)
      }
  
    // @LINE:11
    case controllers_HomeController_allHello5_route(params@_) =>
      call { 
        controllers_HomeController_allHello5_invoker.call(HomeController_0.allHello)
      }
  
    // @LINE:12
    case controllers_HomeController_verboseIndex6_route(params@_) =>
      call { 
        controllers_HomeController_verboseIndex6_invoker.call(HomeController_0.verboseIndex)
      }
  
    // @LINE:13
    case controllers_HomeController_concurrent7_route(params@_) =>
      call(params.fromPath[Integer]("seconds", None)) { (seconds) =>
        controllers_HomeController_concurrent7_invoker.call(HomeController_0.concurrent(seconds))
      }
  
    // @LINE:16
    case controllers_Assets_versioned8_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned8_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
