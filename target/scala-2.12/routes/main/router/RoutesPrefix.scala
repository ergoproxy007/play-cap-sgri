// @GENERATOR:play-routes-compiler
// @SOURCE:D:/play-framework/git/play-cap-sgri/conf/routes
// @DATE:Thu Jan 24 00:57:05 COT 2019


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
