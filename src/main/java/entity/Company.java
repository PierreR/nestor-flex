package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: Nov 19, 2009
 */
@Entity
public class Company {
    @Id @GeneratedValue
    int companyId;
    @OneToMany(mappedBy="company")
    private Set<Employee> employees = new HashSet<Employee>();
    
    private String address, city, industry, name, state, zip;


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    public Set<Employee> getEmployees() { return employees; }

    public void setEmployees(Set<Employee> employees) { this.employees = employees;}


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyId != company.companyId) return false;
        if (address != null ? !address.equals(company.address) : company.address != null) return false;
        if (city != null ? !city.equals(company.city) : company.city != null) return false;
        if (industry != null ? !industry.equals(company.industry) : company.industry != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (state != null ? !state.equals(company.state) : company.state != null) return false;
        if (zip != null ? !zip.equals(company.zip) : company.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        return result;
    }
}
