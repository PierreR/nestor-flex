package srv;

import entity.Municipality;
import entity.Program;
import enu.ContractType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.Set;

import static junit.framework.Assert.assertEquals;


/**
 * Date: Nov 18, 2009
 */
public class ProgramSRVTest extends DBBootStrapper {

    private static int TEST_DATA_COUNT = 2;

    private ProgramSRV srv;

    @BeforeClass
    public void fixture() {
        EntityManager em = injector.getInstance(EntityManager.class);
        em.getTransaction().begin();

        Municipality bruxelles = new Municipality();
        bruxelles.setName("Bruxelles");
        em.persist(bruxelles);

        Program program = new Program();
        program.setMunicipality(bruxelles);
        em.persist(program);
        program = new Program();
        em.persist(program);
        em.getTransaction().commit();

    }

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(ProgramSRV.class);
    }

    @Test
    public void createProgram() {
        EntityManager em = injector.getInstance(EntityManager.class);
        em.getTransaction().begin();
        Program program = new Program();
        program.setName(EXPECTED_NAME);
        em.persist(program);
        em.getTransaction().commit();
        int id = program.getId();
        TEST_DATA_COUNT = TEST_DATA_COUNT + 1;
        assertEquals(TEST_DATA_COUNT, id);

        program = em.find(Program.class, id);
        assertEquals(EXPECTED_NAME, program.getName());
        assertEquals(ContractType.AREA_CONTRACT, program.getContractType());
    }

    @Test
    public void findAll() {
        Set<Program> programs = srv.findAll();
        assertEquals(TEST_DATA_COUNT, programs.size());

    }
}


