package nestor.component {

import flash.events.Event;

import mx.core.Container;

import nestor.component.layout.RecipientLayout;
import nestor.component.layout.RecipientLayout;
import nestor.model.Recipient;

public class RecipientPicker extends PickerComponent {


    public function RecipientPicker() {
        super();
        extraLayout = new RecipientLayout();
    }

    /**
     * AS does not support overloading
     */
     override public function save(event:Event):void {
        var picker:Recipient  =  new Recipient();
        picker.postalCode = RecipientLayout(extraLayout).postalCode.text;
        super._save(picker);
    }
}
}