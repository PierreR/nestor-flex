package nestor.entity;

import javax.persistence.MappedSuperclass;

/**
 * It is probably better to keep an abstract one:
 *   You will have to specify security roles anyway.
 * 
 * Date: Nov 30, 2009
 */
@MappedSuperclass
public abstract class PickerEntity extends BaseEntity {

    String  name_fr, name_nl;

    public void setName_fr(String name_fr) {
        this.name_fr = name_fr;
    }

    public String getName_fr() {
        return name_fr;
    }


    public String getName_nl() {
        return name_nl;
    }

    public void setName_nl(String name_nl) {
        this.name_nl = name_nl;
    }

}
