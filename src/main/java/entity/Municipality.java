package entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Date: Nov 24, 2009
 */

@Entity
public class Municipality extends BaseEntity {

    String postalCode = "";

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        assert postalCode != null;
        this.postalCode = postalCode;
    }
}
