package nestor.model{
import mx.collections.ArrayCollection;

/**
 * This kind of class should be generated automatically.
 */
[Bindable]
[RemoteClass(alias="nestor.entity.Program" )]
public class Program extends BaseEntity {

    public function Program() {
        super();
    }
    public var contractType:ContractType;
    public var managerName:String;
    public var bureau:Bureau;
    public var recipient:Recipient;
    public var grantDate:Date;
    public var notificationDate:Date;
    public var managerDesignationDate:Date;
    public var councilDate:Date;
    public var bureauDesignationDate:Date;
    public var municipality:Bureau;
    public var plannings:ArrayCollection;

}
}