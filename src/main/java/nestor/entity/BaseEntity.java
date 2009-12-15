package nestor.entity;

import nestor.utils.StringUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


/**
 * Right now, the choice is to use this abstract base class for EVERY entities including aggregation association (such as Address).
 *
 * We could separate the trait into two using a simpler one for aggregation entities or even better use multiple interfaces.
 *
 * Java is not that flexible in term of "traits" and composition (Delegation pattern);
 * Another option is the class is getting too big might be to use Aspects.
 *
 * For now, let's try to keep the trait as simple, light and flexible as it could possibly be.
 * Any kind of validations rules should not be implemented here.
 * The only field that cannot be null is the id.
 * 
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
        return id == null ? super.hashCode() : 17 + id;
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
