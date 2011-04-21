
/***********************************************************************
 * MobileThing class
 * 
 * Implements mobile things, aka located artifacts that can move
 *
 ********************************************************************** */


object MobileThing {

    def create (name:String, location:Container):MobileThing = {
        val obj = new MobileThing(name,location)
        obj.install()
        obj
    }
}


class MobileThing protected (n:String,l:Container)
                                extends Thing(n,l) {

    // the location() of a Mobile thing is its original location
    private var mobileLocation:Container = l

    override def location ():Container = mobileLocation

    def changeLocation (l:Container):Unit = {
        mobileLocation.delThing(this)
        l.addThing(this)
        mobileLocation = l
    }

    override def destroy ():Unit = {
        super.destroy()
        // move the destroyed object to Limbo
        mobileLocation=Limbo
    }

    def enterRoom ():Unit = ()

    def leaveRoom ():Unit = ()

    def creationSite ():Container = super.location()

    override def checkMobileThing ():Option[MobileThing] = Option.some(this)

}

