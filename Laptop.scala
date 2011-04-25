
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
    extends MobileThing(n,l) with Usable {

    // this is most definitely a hack to get around
    // Computer not being a trait
    def use (user:Person):Unit = {
        val tempComputer = Computer.create("",Limbo)
        tempComputer.use(user)
    }

    override def checkUsable ():Option[Usable] = Option.some(this)

}
