
trait Subscriber[D,R] {

    def notify (data:D):R
}
