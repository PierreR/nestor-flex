package nestor.model {

[Bindable]
[RemoteClass(alias="entity.Recipient")]
public class Recipient extends Picker
{
    public function Recipient()  {
    }

    public var postalCode:String;
}
}