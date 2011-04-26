
/***********************************************************************
 * Seppuku class
 * 
 * Implements the "seppuku" verb
 * 
 ********************************************************************** */


object Seppuku {

    def create ():Verb = new Seppuku
}

// every game needs a way to commit ritualistic suicide!
class Seppuku protected extends Verb("seppuku") {

    override def action ():Result = {
        Adventure.me().say("I can't handle it anymore!")
        Adventure.me().die()
        Result.REMAIN
    }

}
