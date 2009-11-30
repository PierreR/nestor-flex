package nestor.entity;

import nestor.utils.StringUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


/**
 * Date: Nov 29, 2009
 */

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id @GeneratedValue
    Integer id;
    String name;
    Date creationDate;
    Date modifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity baseEntity = (BaseEntity) o;

        if (id.equals(baseEntity.id)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id;
    }

    @Override
    public String toString() {
        if (StringUtils.isEmpty(getName())) {
            return super.toString();
        } else {
            return getName() + " - " + super.toString();
        }
    }
}
