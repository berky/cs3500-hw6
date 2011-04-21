
/***********************************************************************
 * CompletedHomework class
 * 
 * Implements completed homeworks (a special kind of homework)
 *
 ********************************************************************** */

object CompletedHomework {

    def create (name:String,location:Container):CompletedHomework = {
        val obj = new CompletedHomework(name,location)
        obj.install()
        obj
    }
}


class CompletedHomework protected (n:String,l:Container) 
    extends Homework("completed-" + n,l) {

    private val core:String = Util.noSpaces(n)

    def coreName () = core

    override def checkCompletedHomework ():Option[CompletedHomework] = 
        Option.some(this)
}

