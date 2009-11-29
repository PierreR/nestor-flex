package nestor.model{

/**
 * This kind of class should be generated automatically.
 */
[Bindable]
[RemoteClass(alias="entity.Program" )]
public class Program extends BaseEntity {

    public function Program() {
        super();
    }

    public var managerName:String;
    public var bureauName:String;

    public var grantDate:Date;
    public var notificationDate:Date;
    public var managerDesignationDate:Date;
    public var councilDate:Date;
    public var bureauDesignationDate:Date;
    public var municipality:Municipality;

}
}