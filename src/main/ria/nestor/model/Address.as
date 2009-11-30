package nestor.model {

[Bindable]
[RemoteClass(alias="nestor.entity.Address")]
public class Address {
    public function Address() {
    }

    public var number:String;
    public var postalCode:String;
    public var municipality:String;
    public var line:String;

}
}