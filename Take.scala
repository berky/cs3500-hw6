
/***********************************************************************
 * Take class
 * 
 * Implements the "take" verb
 * 
 ********************************************************************** */


object Take {

    def create ():Verb = new Take()
}


class Take protected () extends Verb("take") {

    override def action (obj:Thing):Result = {
        Adventure.me().take(obj)
        Result.REMAIN
    }
}
