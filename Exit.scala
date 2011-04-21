
/***********************************************************************
 * Exit class
 * 
 * Implements exits from places
 * Technically an artifact, but this is a bit artificial
 *
 */

object Exit {

    def create (from:Room, to:Room, direction:String):Exit = {
        val obj = new Exit(from,to,direction)
        obj.install()
        obj
    }
}

class Exit protected (f:Room,t:Room,d:String) extends Artifact(d) {

    override protected def install ():Unit = {
        super.install()
        this.from().addExit(this)
    }
    
    def from ():Room = f

    def to ():Room = t

    def direction ():String = d

    def use (who:MobileThing):Boolean = {
        who.leaveRoom()
        // the next checkRoom().valOf() should always succeed
        who.location().checkRoom().valOf().report(who.name() + " moves from " + 
					          who.location().name() + " to " + 
					          t.name())
        who.changeLocation(t)
        who.enterRoom()
        true
    }

    // Probably unused, implemented for debugging purposes
    //
    override def toString ():String =
        "Exit from " + f.name() + " to " + t.name() + " via " + d;
}
