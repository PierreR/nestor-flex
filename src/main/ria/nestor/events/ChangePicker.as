package nestor.events {
import flash.events.Event;

import nestor.model.Picker;

/**
 *
 */
public class ChangePicker extends Event {

    public var picker:Picker;
    public function ChangePicker(picker:Picker) {
        super(TYPE);
        this.picker = picker;
    }

    public static const TYPE:String = "ChangePickerEvent";

}
}