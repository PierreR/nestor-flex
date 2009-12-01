package nestor.model {
import mx.rpc.remoting.RemoteObject;


public class BaseEntity {

    private var srv:RemoteObject;

    public function BaseEntity() {
    }

    public var id:Number;

    public var name:String;
    public var creationDate:Date;
    public var modifiedDate:Date;


}
}