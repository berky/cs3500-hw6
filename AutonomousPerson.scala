
/***********************************************************************
 * AutonomousPerson class
 * 
 * Implements persons that can move or act on their own
 *
 ********************************************************************** */


object AutonomousPerson {

    def create (name:String, birthplace:Room,
	        restlessness:Int,miserly:Int):AutonomousPerson = {
        val obj = new AutonomousPerson(name,birthplace,
				       restlessness,miserly)
        obj.install()
        obj
    }
}


class AutonomousPerson protected (n:String,
				  birthplace:Room,
				  r:Int,
				  miserly:Int) extends Person(n,birthplace) {

    private var restlessness = r

    override protected def install ():Unit = {
        super.install()
        Adventure.clock().register(moveAndTakeStuffAction,10)
    }

    protected def moveAndTakeStuffAction (i:Int):Unit = 
        if (!isInLimbo()) {
            if (Util.random(restlessness) == restlessness)
	        moveSomewhere()
            if (Util.random(miserly) == miserly)
	        takeSomething()
        }

    protected def moveSomewhere ():Unit = {
        // this checkRoom().valOf() should always succeed
        val exit : Option[Exit] =  location().checkRoom().valOf().randomExit()
        if (exit.isSome())
            this.goExit(exit.valOf())
    }

    protected def takeSomething ():Unit = {
        val l = stuffAround().append(peekAround())
        if (!l.isEmpty())
            take(Util.pickRandom(l))
    }

    def changeRestlessness (newRestlessness:Int):Unit = {
        restlessness = newRestlessness
    }

    override def die ():Unit = {
        say("SHREEEEEK! I, uh, suddenly feel very faint...")
        super.die()
    }
}
