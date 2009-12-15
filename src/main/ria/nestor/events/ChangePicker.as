package nestor.events {
import flash.events.Event;

import nestor.model.BaseEntity;
import nestor.model.Picker;

/**
 *  Generic event when a record have been changed or created
 */
public class ChangePicker extends Event {

    public var entity:BaseEntity;
    public function ChangePicker(picker:BaseEntity) {
        super(TYPE);
        this.entity = picker;
    }

    public static const TYPE:String = "ChangePickerEvent";

}
}