
/***********************************************************************
 * Quit class
 * 
 * Implements the "quit" verb
 *
 ********************************************************************** */


object Quit {

    def create ():Verb = new Quit()
}


class Quit protected () extends Verb("quit") {

    override def action ():Result = {
        println("Goodbye")
        System.exit(0)
        Result.REMAIN
    }
}
