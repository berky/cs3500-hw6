
/***********************************************************************
 * DirectionVerb class
 * 
 * Implements the various go-in-some-direction verbs
 *
 ********************************************************************** */


object DirectionVerb {

    def create (word:String):Verb = new DirectionVerb(word)
}



class DirectionVerb protected (w:String) extends Verb(w) {

    override def action ():Result = 
        if (Adventure.me().go(this.word()))
            return Result.ADVANCE
        else 
            return Result.REMAIN
}
