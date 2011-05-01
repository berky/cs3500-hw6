
/***********************************************************************
 * Laptop class
 * 
 * Implements laptops, which are mobile computers
 *
 ********************************************************************** */

object Laptop {

    def create (name:String,location:Container):Laptop = {
        val obj = new Laptop(name,location)
        obj.install()
        obj
    }
}


class Laptop protected (n:String,l:Container) 
    extends MobileThing(n,l) with Computable {

    override def checkUsable ():Option[Usable] = Option.some(this)
    override def checkComputable ():Option[Computable] = Option.some(this)
}
