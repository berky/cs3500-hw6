
/***********************************************************************
 * Cheat class
 * 
 * Implements the "cheat" verb
 * Show the location of all items in the world
 * 
 ********************************************************************** */


object Cheat {

    def create ():Verb = new Cheat
}


class Cheat protected extends Verb("cheat") {

    override def action ():Result = {

        Adventure.me().say("All of a sudden I feel all-seeing...")

        for (thing <- Adventure.worldThings())
            Adventure.me().say("  " + thing.name() + 
                               " is in " + thing.location().name())
        
        Result.REMAIN
    }
}
