package srv;

import entity.Municipality;
import entity.Program;
import enu.ContractType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;

import static org.testng.AssertJUnit.*;


/**
 * Date: Nov 18, 2009
 */
public class ProgramServiceTest extends DBBootStrapper {

    private ProgramService srv;
    private dao.Program dao;
    private static final String PROGRAM_NAME_BRUXELLES = "program.name.bruxelles";

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
        program.setName(PROGRAM_NAME_BRUXELLES);
        program.setMunicipality(bruxelles);
        em.persist(program);

        em.getTransaction().commit();

    }

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(ProgramService.class);
        dao = injector.getInstance(dao.Program.class);
    }


    @Test
    public void create() {
        int progNb = srv.listAll().size();
        Program program = new Program();
        program.setName(EXPECTED_NAME);

        Program created = srv.save(program);
        EntityManager em = injector.getInstance(EntityManager.class);

        assertFalse("The entity should be detached after the transaction completes", em.contains(created));

        int id = program.getId();
        assertTrue(id > 0);
        assertEquals(progNb + 1, srv.listAll().size());

        program = em.find(Program.class, id);
        assertEquals(EXPECTED_NAME, program.getName());
        assertEquals(ContractType.AREA_CONTRACT, program.getContractType());

    }


    @Test
    public void find() {
        Program program = dao.findByMunipality("Bruxelles");
        assertEquals(PROGRAM_NAME_BRUXELLES, program.getName());
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


