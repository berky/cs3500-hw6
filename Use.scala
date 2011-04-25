
/***********************************************************************
 * Use class
 * 
 * Implements the "use" verb
 *
 ***********************************************************************/


object Use {

  def create ():Verb = new Use()
}



class Use protected () extends Verb("use") {

  override def action (obj:Thing):Result = {
    if (obj.checkUsable().isNone()) {
      Adventure.me().say ("I try but cannot use " + obj.name())
    } else {
      val usableObj : Usable = obj.checkUsable().valOf()
      usableObj.use(Adventure.me())
    } 
    Result.REMAIN
  }
}
