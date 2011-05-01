
/***********************************************************************
 * Wait class
 * 
 * Implements the "wait" verb
 *
 ********************************************************************** */


object Wait {

    def create ():Verb = new Wait()
}


class Wait protected ()extends Verb("wait") {

    override def action ():Result = Result.ADVANCE
}
