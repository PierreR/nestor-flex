package entity;

import javax.persistence.*;

/**
 * Date: Nov 30, 2009
 */

@Entity
public class Recipient extends PickerEntity {

    String postalCode;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}


