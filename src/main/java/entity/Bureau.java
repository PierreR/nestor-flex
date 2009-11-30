package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Date: Nov 30, 2009
 */
@Entity
public class Bureau extends PickerEntity {

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
