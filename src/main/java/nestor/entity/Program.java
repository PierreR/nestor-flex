package nestor.entity;

import javax.persistence.*;
import java.io.*;
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

    @OneToOne
    ContractType contractType; // don't try the enum

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Planning> plannings = new HashSet<Planning>();

    /**
     * TODO check the business rules about the contractType name in here !
     */
    public String getReference() {
        return (getContractType() != null ? getContractType().getName_fr() : "") +
                (getRecipient() != null ? getRecipient().getPostalCode() : "");
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

    public Set<Planning> getPlannings() {
        return plannings;
    }

    public void setPlannings(Set<Planning> planning) {
        this.plannings = planning;
    }

    public Bureau getBureau() {
        return bureau;
    }

    public void setBureau(Bureau bureau) {
        this.bureau = bureau;
    }

    public void addPlanning(Planning planning) {
        this.getPlannings().add(planning);
        planning.setProgram(this);
    }

    public void writeExternal(ObjectOutput out) throws IOException {

    }



    @Entity()
    public static class Planning extends BaseEntity {
        Date date;

        @ManyToOne
        Program program;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Program getProgram() {
            return program;
        }


        public void setProgram(Program program) {
            this.program = program;
        }
    }
}
