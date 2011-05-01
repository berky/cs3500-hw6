
/***********************************************************************
 * GPSTracker class
 *
 * A Usable MobileThing that tells the location of every Person in
 * the world
 *
 ********************************************************************** */


object GPSTracker {

    def create (name:String,location:Container):GPSTracker = {
        val obj = new GPSTracker(name,location)
        obj.install()
        obj
    }
}

class GPSTracker protected (n:String,l:Container) 
    extends MobileThing(n,l) with Usable {

    def use (user:Person):Unit = {

        Adventure.me().say("I fiddle with the buttons on the " + n)
        
        // we know it's a list of Person, not MobileThing, so
        // location().checkRoom().valOf() should always succeed
        for (person <- Adventure.people())
            Adventure.me().say("  " + person.name() + 
                               " is in " + 
                               person.location().checkRoom().valOf().name())
    }

    override def checkUsable ():Option[Usable] = Option.some(this)

}
