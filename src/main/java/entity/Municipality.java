package entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Date: Nov 24, 2009
 */

@Entity
public class Municipality {

    @Id @GeneratedValue
    int id;

    String name;

    String postalCode = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        assert postalCode != null;
        this.postalCode = postalCode;
    }
}
