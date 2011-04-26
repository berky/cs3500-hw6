
/***********************************************************************
 * Professor class
 * 
 * Implements professors
 *
 ********************************************************************** */


object Professor {

    def create (name:String,birthplace:Room,speed:Int,miserly:Int):Professor = {
        val obj = new Professor(name,birthplace,speed,miserly)
        obj.install()
        obj
    }
}


class Professor protected (n:String,l:Room,s:Int,m:Int) 
                           extends AutonomousPerson(n,l,s,m) {

    override def ask ():Unit = {
        for (t <- things())
            if (t.checkGradedHomework().isSome()) {
	        say("Look at that! Someone may actually pass this course!")
	        say("Congratulations!")
	        System.exit(0)
            }
        say("Pray tell -- why are you bothering me now?")
    }
}
