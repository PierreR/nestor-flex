package dao;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;
import entity.Employee;

import java.util.Collection;

/**
 * Date: Nov 24, 2009
 */
public interface CompanyQ {
    @Finder(query = "from Employee")
    public Collection<Employee> listAll();

    @Finder(query = "select e from Employee e join e.company c where c.name=:companyName")
    public Collection<Employee> findByCompanyName(@Named("companyName") String name);

}
