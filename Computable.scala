
/***********************************************************************
 * Computable trait
 * 
 * Implements the basic functionality of computers
 *
 ********************************************************************** */

trait Computable extends Usable {

    def use (user:Person):Unit = {

        def isUnfinished (t:Thing):Boolean = 
            t.checkUnfinishedHomework.isSome()
        def toUnfinished (t:Thing):UnfinishedHomework = 
            t.checkUnfinishedHomework.valOf()

        val homeworks = 
            user.things().filter(isUnfinished).map(toUnfinished)
        
        if (homeworks.isEmpty()) {
            user.say ("Aargh! This computer only has vi," + 
                      " the editor of the beast!");
        } else {
            for (uhw <- homeworks) {
	        user.say("Okay, time to finish " + uhw.name());
	        CompletedHomework.create(uhw.coreName(),
				         user.location())
	        uhw.destroy()
            }
        }
    }
}
