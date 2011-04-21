
/***********************************************************************
 * GradedHomework class
 * 
 * Implements graded homeworks (a special kind of homework)
 *
 ***********************************************************************/


object GradedHomework {

  def create (name:String,location:Container):GradedHomework = {
    val obj = new GradedHomework(name,location)
    obj.install()
    obj
  }
}


class GradedHomework protected (n:String,l:Container) 
                                      extends Homework("graded-"+n,l) {
  
  val core = Util.noSpaces(n)

  def coreName ():String = core

  override def checkGradedHomework ():Option[GradedHomework] = Option.some(this)

}

