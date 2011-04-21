
/***********************************************************************
 * God class
 * 
 * Implements the "god" verb
 *
 ********************************************************************** */


object God {

    def create ():Verb = new God()
}


class God protected () extends Verb("god") {
    
    override def action ():Result = {
        Adventure.toggleGodMode()
        println("Setting god mode to " + Adventure.isGodMode())
        Result.REMAIN
    }

}
