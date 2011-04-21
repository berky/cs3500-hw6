
/***********************************************************************
 * Drop class
 * 
 * Implements the "drop" verb
 *
 ********************************************************************** */

object Drop {

    def create ():Verb = new Drop()
}


class Drop protected () extends Verb("drop") {

    override def action (obj:Thing):Result = {
        if (!Adventure.me().haveThing(obj)) 
            Adventure.me().say("I am not carrying " + obj.name())
        else 
            // If I'm carrying the object, then it must be a MobileThing
            Adventure.me().drop(obj.checkMobileThing().valOf())
        Result.REMAIN
    }


}
