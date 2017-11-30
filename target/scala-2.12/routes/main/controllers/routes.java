
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/saikrishna/Alpha/AA_ASSMNT/conf/routes
// @DATE:Fri Dec 01 02:44:54 IST 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAAController AAController = new controllers.ReverseAAController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAAController AAController = new controllers.javascript.ReverseAAController(RoutesPrefix.byNamePrefix());
  }

}
