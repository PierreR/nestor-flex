package entity;

import enu.ContractType;
import entity.Recipient;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: Nov 24, 2009
 */
@Entity
public class Program extends BaseEntity {
    String managerName;                // name of the project manager
    Date grantDate,
         notificationDate,              // notification to the commune
         managerDesignationDate,        // designation of the project manager
         councilDate,                   //  commune council
         bureauDesignationDate;

    @OneToOne
    Recipient recipient;  // body that will benefit from the Program ("commune", "cpas", ...)

    @OneToOne
    Bureau bureau;       // bureau d'étude

    @Enumerated
    ContractType contractType = ContractType.AREA_CONTRACT;

    @OneToMany(mappedBy = "programId", cascade = CascadeType.ALL)
    Set<Planning> planning = new HashSet<Planning>();

    public String getReference() {
        return getContractType().name() + getContractType().name() +
               (getRecipient() != null ? getRecipient().getPostalCode() : "") ;
    }


    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Date getManagerDesignationDate() {
        return managerDesignationDate;
    }

    public void setManagerDesignationDate(Date managerDesignationDate) {
        this.managerDesignationDate = managerDesignationDate;
    }

    public Date getCouncilDate() {
        return councilDate;
    }

    public void setCouncilDate(Date councilDate) {
        this.councilDate = councilDate;
    }

    public Date getBureauDesignationDate() {
        return bureauDesignationDate;
    }

    public void setBureauDesignationDate(Date bureauDesignationDate) {
        this.bureauDesignationDate = bureauDesignationDate;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        assert recipient != null;
        this.recipient = recipient;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Set<Planning> getPlanning() {
        return planning;
    }

    public void setPlanning(Set<Planning> planning) {
        this.planning = planning;
    }

    public Bureau getBureau() {
        return bureau;
    }

    public void setBureau(Bureau bureau) {
        this.bureau = bureau;
    }

    @Entity()
    public static class Planning {
        @Id @GeneratedValue
        int id;
        String description;
        Date date;

        @ManyToOne
        Program programId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Program getProgramId() {
            return programId;
        }

        public void setProgramId(Program programId) {
            this.programId = programId;
        }
    }
}
