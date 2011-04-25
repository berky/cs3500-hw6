
/***********************************************************************
 * Ask class
 * 
 * Implements the "ask" verb
 *
 ********************************************************************** */


object Ask {

    def create ():Verb = new Ask
}


class Ask protected extends Verb("ask") {

    override def action (obj:Thing):Result =  {
        if (obj.checkPerson().isSome()) 
            obj.checkPerson().valOf().ask()
        else
            Adventure.me().say("I feel weird asking something of " + 
			       obj.name())
        Result.REMAIN
    }
    
}
