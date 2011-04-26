
/***********************************************************************
 * Thing class
 * 
 * Implements things, aka located artifacts
 *
 ********************************************************************** */


object Thing {

    def create (name:String,location:Container):Thing = {
        val obj = new Thing(name,location)
        obj.install()
        obj
    }
}


class Thing protected (n:String,l:Container) extends Artifact(n)  {

    private var theLocation : Container = l

    override protected def install ():Unit = {
        super.install()
        location().addThing(this)
        if (Adventure.isGodMode())
            println("("+name() + " is created in " + location().name() + ")")
    }

    def location ():Container = theLocation

    def isInLimbo ():Boolean = (location() == Limbo)

    override def destroy ():Unit = {
        location().delThing(this)
        super.destroy()
        // move to Limbo -- this keeps location non-null
        theLocation=Limbo
        if (Adventure.isGodMode())
            println("("+name() + " is destroyed)")
    }

    def report (text:String):Unit =
        // this checkRoom().valOf() should always succeed
        location().checkRoom().valOf().report("At " + location().name() + 
					      " " + text)

    def broadcast (text:String):Unit = 
        println(text)

    override def checkThing ():Option[Thing] =
        Option.some(this)
}
