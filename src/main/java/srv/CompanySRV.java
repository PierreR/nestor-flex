package srv;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import dao.CompanyQ;
import entity.Company;
import entity.Employee;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Date: Nov 24, 2009
 */

public class CompanySRV {

    @Inject CompanyQ dao;
    @Inject EntityManager em;

    @Transactional
    public Company create(Company company) {
        //validate
        // create
        em.persist(company);
        return company;
    }

    public Company findById(int id) {
        return em.find(Company.class, id);
    }

    public Collection<Employee> listAll() {
        return dao.listAll();
    }

    public Collection<Employee> findByCompanyName(String name) {
        return dao.findByCompanyName(name);
    }
}
