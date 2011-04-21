
/***********************************************************************
 * Artifact class
 * 
 * Implements the core functionality of artifacts
 * 
 ********************************************************************** */


object Artifact {

    def create (name:String):Artifact = {
        val art = new Artifact(Util.noSpaces(name))
        art.install()
        art
    }
}


class Artifact protected (n:String) {

    def name ():String = n
    
    protected def install ():Unit = ()
        
    def destroy ():Unit = ()
            
    def checkThing ():Option[Thing] = 
        Option.none()
    
    def checkMobileThing ():Option[MobileThing] = 
        Option.none()

    def checkPerson ():Option[Person] =
        Option.none()

    def checkUsable ():Option[Usable] =
        Option.none()
    
    def checkHomework ():Option[Homework] =
        Option.none()

    def checkUnfinishedHomework ():Option[UnfinishedHomework] = 
        Option.none()
    
    def checkCompletedHomework ():Option[CompletedHomework] = 
        Option.none()
    
    def checkGradedHomework ():Option[GradedHomework] = 
        Option.none()

    def checkRoom ():Option[Room] = 
        Option.none()
}
