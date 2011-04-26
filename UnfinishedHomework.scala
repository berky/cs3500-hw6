
/***********************************************************************
 * UnfinishedHomework class
 * 
 * Implements unfinished homeworks (a special kind of homework)
 *
 ********************************************************************** */

object UnfinishedHomework {

  def create (name:String,location:Container):UnfinishedHomework = {
    val obj = new UnfinishedHomework(name,location)
    obj.install()
    obj
  }
}


class UnfinishedHomework protected (n:String,l:Container)
                                    extends Homework("unfinished-"+n,l) {

  private val core : String = Util.noSpaces(n)

  def coreName ():String = core

  override def checkUnfinishedHomework ():Option[UnfinishedHomework] = 
    Option.some(this)
}

