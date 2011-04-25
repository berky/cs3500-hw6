
/***********************************************************************
 * Homework class
 * 
 * Implements homeworks
 * Class is abstract, subclasses will define the kinds of homeworks
 * 
 */


abstract class Homework protected (name:String, location:Container) 
         extends MobileThing(name,location) {

    def coreName ():String

    override def checkHomework ():Option[Homework] = Option.some(this)
}

