// @GENERATOR:play-routes-compiler
// @SOURCE:D:/play-framework/play-cap-sgri/play-cap-sgri/conf/routes
// @DATE:Tue Jan 22 22:35:55 COT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
