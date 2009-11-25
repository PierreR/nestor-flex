package srv;

import entity.Company;
import entity.Employee;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.Collection;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;


/**
 * Date: Nov 18, 2009
 * 
 */
public class CompanySRVTest extends DBBootStrapper {

    private static CompanySRV srv;

    @BeforeClass
    public void fixture() {
        EntityManager em = injector.getInstance(EntityManager.class);
        em.getTransaction().begin();

        Company company = new Company();
        company.setName(EXPECTED_NAME);
        em.persist(company);
        Employee employee = new Employee();
        employee.setFirstName("Sam");
        employee.setCompany(company);
        em.persist(employee);
        em.getTransaction().commit();
    }

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(CompanySRV.class);
    }


    @Test
    public void getEmployees() {
        Collection<Employee> employees = srv.listAll();
        assertFalse(employees.isEmpty());
    }

    @Test
    public void getByCompany() {
        Collection<Employee> employees = srv.findByCompanyName(EXPECTED_NAME);
        assertFalse(employees.isEmpty());
        assertEquals("Sam", employees.iterator().next().getFirstName());
    }

}