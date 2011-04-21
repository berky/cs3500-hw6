
/***********************************************************************
 * Look class
 * 
 * Implements the "look" verb
 *
 * 
 */


object Look {

    def create ():Verb = new Look()
}


class Look protected () extends Verb("look") {

    override def action ():Result = {
        Adventure.me().lookAround()
        Result.REMAIN
    }
}
