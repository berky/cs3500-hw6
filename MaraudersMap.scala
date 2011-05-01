
/***********************************************************************
 * MaurauderMap class
 * 
 * "cheat" and GPSTracker all rolled into one
 * 
 ********************************************************************** */


object MaraudersMap {

    def create (name:String,location:Container):MaraudersMap = {
        val obj = new MaraudersMap(name,location)
        obj.install()
        obj
    }
}


class MaraudersMap protected (n:String,l:Container) 
    extends Thing(n,l) with Usable {

    def use (user:Person):Unit = {

        Adventure.me().say("I solemnly swear that I am up to no good.")

        // location of all people
        println("----------------------------------------")
        
        // we know it's a list of Person, not MobileThing, so
        // location().checkRoom().valOf() should always succeed
        for (person <- Adventure.people())
            Adventure.me().say("  " + person.name() + 
                               " is in " + 
                               person.location().checkRoom().valOf().name())

        
        // location of all other things
        println("----------------------------------------")
        
        for (thing <- Adventure.worldThings())
            Adventure.me().say("  " + thing.name() + 
                               " is in " + thing.location().name())

        println("----------------------------------------")
        Adventure.me().say("Mischief managed.")
    }
  
    override def checkUsable ():Option[Usable] = Option.some(this)
}
