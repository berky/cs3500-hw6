
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
    extends Thing(n,l) with Usable {

    def use (user:Person):Unit = {

        def isUnfinished (t:Thing):Boolean = 
            t.checkUnfinishedHomework.isSome()
        def toUnfinished (t:Thing):UnfinishedHomework = 
            t.checkUnfinishedHomework.valOf()

        val homeworks = 
            user.things().filter(isUnfinished).map(toUnfinished)
        
        if (homeworks.isEmpty()) {
            user.say ("Aargh! This computer is near useless!" + 
                      " It only has vi, the editor of the beast!");
        } else {
            for (uhw <- homeworks) {
	        user.say("Okay, time to finish " + uhw.name());
	        CompletedHomework.create(uhw.coreName(),
				         user.location())
	        uhw.destroy()
            }
        }
    }

    override def checkUsable ():Option[Usable] = Option.some(this)
}
