
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/saikrishna/Alpha/AA_ASSMNT/conf/routes
// @DATE:Fri Dec 01 02:44:54 IST 2017


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
