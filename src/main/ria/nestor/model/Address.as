package nestor.model {

[Bindable]
[RemoteClass(alias="nestor.entity.Address")]
public class Address extends BaseEntity {
    public function Address() {
    }
    public var postalCode:String;
    public var municipality:String;;

}
}