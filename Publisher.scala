
trait Publisher[D,R] {
    
    def subscribe (sub:Subscriber[D,R]):Unit
}
