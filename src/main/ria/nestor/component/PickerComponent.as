package nestor.component {
import flash.events.Event;

import mx.containers.Panel;
import mx.core.Container;
import mx.managers.PopUpManager;
import mx.rpc.events.ResultEvent;
import mx.rpc.remoting.RemoteObject;

import nestor.component.layout.PickerLayout;
import nestor.model.Picker;

public class PickerComponent extends Panel {

    private var pickerLayout:PickerLayout;
    protected var extraLayout: Container;
    
    private var srv:RemoteObject = new RemoteObject("picker");

    public function PickerComponent()
    {
        super();
        srv.save.addEventListener("result", resultHandler);
        srv.addEventListener("fault", faultHandler);

    }

    override protected function createChildren():void {
        super.createChildren();
        pickerLayout = new PickerLayout();
        addChild(pickerLayout);
        pickerLayout['saveButtonId'].addEventListener('click', this.save);
        pickerLayout['cancelButtonId'].addEventListener('click', this.cancel);
        pickerLayout['boxId'].addChild(extraLayout);
    }

    public function save(event:Event):void {
    }

    public function cancel(event:Event):void {
        close();
    }


    public function _save(picker:Picker):void
    {
        picker.name_fr = pickerLayout.name_fr.text;
        picker.name_nl = pickerLayout.name_nl.text;
        srv.save(picker);

    }


    private function resultHandler(event:ResultEvent):void
    {
        close();

    }

    private function faultHandler(event:ResultEvent):void
    {
    }

    private function close():void 
    {
        PopUpManager.removePopUp(this);
    }

}
}