
/***********************************************************************
 * Give class
 * 
 * Implements the "give" verb
 *
 ********************************************************************** */


object Give {

    def create ():Verb = new Give()
}


class Give protected () extends Verb("give") {
    
    override def action (obj:Thing, indObj:Thing):Result = {
        if (!Adventure.me().haveThing(obj)) 
            println("Not carrying " + obj.name())
        else if (indObj.checkPerson().isNone()) {
            println("Target " + indObj.name() + " not a person")
        } else {
            val target = indObj.checkPerson().valOf()
            // If we carry the object, then it must be a MobileThing
            val mobileObj = obj.checkMobileThing.valOf()
            // If we have the target just take the object from us, then 
            // our persona will complain that the object was lost
            // let's drop it on the floor first
            Adventure.me().drop(mobileObj)
            target.take(mobileObj)
        }
        return Result.REMAIN
    }

}
