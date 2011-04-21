
/***********************************************************************
 * PrlGrad class
 * 
 * Implements PRL graduate students
 *
 ********************************************************************** */


object PrlGrad {

    def create (name:String,birthplace:Room,speed:Int,anger:Int):PrlGrad = {
        val obj = new PrlGrad(name,birthplace,speed,anger)
        obj.install()
        obj
    }
}

class PrlGrad protected (n:String,l:Room,s:Int,anger:Int)
                            extends AutonomousPerson(n,l,s,1000) {

    // note: it is very unlikely that the PrlGrad grabs something

    override protected def install ():Unit = {
        super.install()
        Adventure.clock().register(snatchHomeworkAction,10)
    }

    private def snatchHomeworkAction (i:Int):Unit = 
        if (!isInLimbo())
            snatchHomework()

    def snatchHomework ():Unit = {
        if (Util.random(anger) == anger) {
            val stuff = stuffAround().append(peekAround())
            if (!stuff.isEmpty()) {
	        for (t <- stuff) 
	            if (t.checkHomework().isSome()) {
	                take(t)
	                say("Wait a minute! This is not Scheme!?...")
	                say("Burn, baby, burn!")
	                report(name() + " burns " + t.name())
	                t.destroy()
	            }
            } else {
	        report(name() + " grumbles something about Scheme")
            }
        }
    }
}

