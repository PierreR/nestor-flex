package nestor.model {

[Bindable]
[RemoteClass(alias="nestor.entity.Program$Planning")]
public class Planning extends BaseEntity {
    public function Planning() {
        super();
    }

    public var date:Date;
    //public var program:Program;
}
}