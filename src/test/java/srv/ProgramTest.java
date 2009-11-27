package srv;

import entity.Municipality;
import entity.Program;
import enu.ContractType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;


/**
 * Date: Nov 18, 2009
 */
public class ProgramTest extends DBBootStrapper {

    private static int TEST_DATA_COUNT = 2;

    private srv.Program srv;
    private dao.Program dao;

    @BeforeClass
    public void fixture() {
        EntityManager em = injector.getInstance(EntityManager.class);
        em.getTransaction().begin();

        Municipality bruxelles = new Municipality();
        bruxelles.setName("Bruxelles");
        bruxelles.setPostalCode("1000");

        Program.Planning planning = new Program.Planning();
        planning.setDate(EXPECTED_DAY_DATE);

        Program program = new Program();
        program.setMunicipality(bruxelles);
        em.persist(program);

        program = new Program();
        em.persist(program);
        em.getTransaction().commit();

    }

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(srv.Program.class);
        dao = injector.getInstance(dao.Program.class);
    }


    @Test
    public void create() {

        Program program = new Program();
        program.setName(EXPECTED_NAME);

        Program created = srv.save(program);
        EntityManager em = injector.getInstance(EntityManager.class);

        assertFalse("The entity should be detached after the transaction completes", em.contains(created));

        int id = program.getId();
        TEST_DATA_COUNT = TEST_DATA_COUNT + 1;
        assertEquals(TEST_DATA_COUNT, id);

        program = em.find(Program.class, id);
        assertEquals(EXPECTED_NAME, program.getName());
        assertEquals(ContractType.AREA_CONTRACT, program.getContractType());

    }


    @Test
    public void find() {
        Set<entity.Program> programs = dao.findAll();
        assertEquals(TEST_DATA_COUNT, programs.size());

        Program program = dao.findByMunipality("Bruxelles");
        assertEquals("1000", program.getMunicipality().getPostalCode());
    }

    @Test
    public void update() {
        Program program = dao.findByMunipality("Bruxelles");
        String expectedPostalCode = "newPostalCode";
        program.getMunicipality().setPostalCode(expectedPostalCode);
        program = srv.save(program);
        assertEquals(expectedPostalCode, program.getMunicipality().getPostalCode());

    }

}


