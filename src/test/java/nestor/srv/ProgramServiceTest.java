package nestor.srv;

import nestor.entity.Program;
import nestor.enu.ContractType;
import nestor.entity.Recipient;
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
    private nestor.dao.Program dao;
    private static final String PROGRAM_NAME_BRUXELLES = "program.name.bruxelles";
    private PickerService pickerService;

    @BeforeClass
    public void fixture() {
        EntityManager em = injector.getInstance(EntityManager.class);
        em.getTransaction().begin();

        Recipient ixelles = new Recipient();
        ixelles.setName_fr("Ixelles");
        ixelles.setName_nl("Elsene");
        ixelles.setPostalCode("1050");

        em.persist(ixelles);

        Recipient bruxelles = new Recipient();
        bruxelles.setName_fr("Bruxelles");
        bruxelles.setName_nl("Brussels");
        bruxelles.setPostalCode("1000");

        em.persist(bruxelles);

        Program program = new Program();
        program.setName(PROGRAM_NAME_BRUXELLES);
        program.setRecipient(bruxelles);

        em.persist(program);

        em.getTransaction().commit();

    }

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(ProgramService.class);
        dao = injector.getInstance(nestor.dao.Program.class);
        pickerService = injector.getInstance(PickerService.class);
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

    }


    @Test
    public void find() {
        Program program = dao.findByBureau("program.bureau.name");
        assertEquals("program.name", program.getName());
        assertEquals("program.bureau.address.postalCode", program.getBureau().getAddress().getPostalCode());
        assertFalse(program.getPlannings().isEmpty());
        Program.Planning planning = program.getPlannings().iterator().next();
        assertEquals("program.planning.1", planning.getName());
        assertEquals(EXPECTED_DATE, planning.getDate());
    }

    @Test
    public void update() {
        Program program = srv.findByRecipient("Bruxelles", "fr");
        Recipient ixelles = pickerService.findRecipientByName("Ixelles", "fr");
        program.setRecipient(ixelles);
        program = srv.save(program);
        assertEquals(ixelles.getPostalCode(), program.getRecipient().getPostalCode());

    }


}


