package nestor.model {
import mx.resources.ResourceManager;


public class Picker extends BaseEntity {
    public function Picker() {
    }
    public var name_fr:String;
    public var name_nl:String;

    public function get label():String {
        var locale:String = ResourceManager.getInstance().localeChain[0];
        if (locale == "fr_BE") {
            return name_fr;
        } else {
            return name_nl;
        }
    }
}
}