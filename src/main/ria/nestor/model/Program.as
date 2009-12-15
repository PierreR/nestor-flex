package nestor.model{
import flash.utils.IDataInput;
import flash.utils.IDataOutput;
import flash.utils.IExternalizable;

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
    public var plannings:ArrayCollection = new ArrayCollection();

    public function getSelected(dataProvider:Object, pickerId:int):Picker {
        for each(var picker:Picker in dataProvider) {
            if (picker.id == pickerId) return picker;
        }
        return null;
    }

    /**
     *  This forces a link with Planning.as in order to manage Collection serialization on the java side
    */
    public function addPlanning(planning:Planning):void {
        plannings.addItem(planning);
    }
}

}