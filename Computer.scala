
/***********************************************************************
 * Computer class
 * 
 * Implements computers
 *
 ********************************************************************** */

object Computer {

    def create (name:String,location:Container):Computer = {
        val obj = new Computer(name,location)
        obj.install()
        obj
    }
}


class Computer protected (n:String,l:Container) 
    extends Thing(n,l) with Computable {

    override def checkUsable ():Option[Usable] = Option.some(this)
    override def checkComputable ():Option[Computable] = Option.some(this)
}
