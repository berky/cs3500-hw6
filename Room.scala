
/***********************************************************************
 * Room class
 * 
 * Implements rooms, aka locations
 *
 ********************************************************************** */

object Room {

    def create (name:String):Room = {
        val obj  = new Room(name)
        obj.install()
        obj
    }
}


class Room protected (n:String) extends Artifact(n) with Container {

    private var theExits : List[Exit] = List.empty()

    override protected def install ():Unit = {
        Adventure.addRoom(this)
    }

    def exits ():List[Exit] = theExits

    def findExitInDirection(direction:String):Option[Exit] = {
        def sameDirection (exit:Exit):Boolean = (direction==exit.direction())
            theExits.filterFirst(sameDirection)
    }

    def exitTowards (direction:String):Option[Exit] = 
        findExitInDirection(direction)
    
    def addExit (exit:Exit):Unit = {
        val direction = exit.direction()
        if (exitTowards(direction).isSome())
            throw new RuntimeException(name() + " already has exit " + direction)
        else
            theExits = List.cons(exit,theExits)
    }

    def randomExit ():Option[Exit] = {
        if (theExits.isEmpty()) 
            Option.none()
        else 
            Option.some(theExits.nth(Util.random(theExits.length())))
    }

    // You see room reports only if you are in the same room
    // or if you have enabled god mode
    def report (msg:String):Unit = {
        if (Adventure.me().location() == this) {
            println(msg);
        } else {
            if (Adventure.isGodMode())
	        println("(" + msg + ")");
        }
    }

    override def checkRoom ():Option[Room] = Option.some(this)
}
