package nestor.srv;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import com.wideplay.warp.persist.jpa.JpaUnit;
import nestor.dao.Picker;
import nestor.entity.Address;
import nestor.entity.Bureau;
import nestor.entity.Program;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.persistence.EntityManager;
import java.sql.Date;

/**
 * The database is only started once at loading time.
 * To be able to start and clean up the db (arguably this is not required) we need to implement TestNG ITestListener.
 * !!!!! To make the Listener effective, it has to be added as a TestRunner parameter (-listener srv.DBBootStrapper) !!!!!
 * I have never found a way to do this with JUnit ...
 * <p/>
 * It is the responsability of every sub TestClasses to create its own fixtures (using @BeforeClass)
 * Common fixtures should not be updated by specific tests !
 * <p/>
 * Guice injector is made available at the package level as a static variable.
 * <p/>
 * Date: Nov 25, 2009
 */
public class DBBootStrapper implements ITestListener {


    static Injector injector;
    static final String EXPECTED_NAME = "expected";
    static final Date EXPECTED_DAY_DATE = new Date(System.currentTimeMillis());
    static final java.util.Date EXPECTED_DATE = new java.util.Date();

    static {
        injector = Guice.createInjector(PersistenceService.usingJpa()
                .across(UnitOfWork.TRANSACTION)
                        //   .forAll(Matchers.any())
                .addAccessor(nestor.dao.Program.class)
                .addAccessor(Picker.class)
                .buildModule(),
                new AbstractModule() {
                    protected void configure() {
                        bindConstant().annotatedWith(JpaUnit.class).to("test");
                    }
                });
    }


    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        injector.getInstance(PersistenceService.class).start();
        createCommonFixtures();
    }

    /**
     * Please follow conventions here:
     * only one instance created max per entity
     * name convention is entityName.fieldName
     */
    private void createCommonFixtures() {

        EntityManager em = injector.getInstance(EntityManager.class);
        em.getTransaction().begin();

        Program program = new Program();
        program.setName("program.name");

        Bureau bureau = new Bureau();
        bureau.setName("program.bureau.name");
        Address address = new Address();
        address.setPostalCode("program.bureau.address.postalCode");
        bureau.setAddress(address);

        em.persist(bureau);

        program.setBureau(bureau);

        Program.Planning planning = new Program.Planning();
        planning.setName("program.planning.1");
        planning.setDate(EXPECTED_DATE);

        //TODO This should not be required !
        //em.persist(planning);


        program.addPlanning(planning);
        em.persist(program);
        em.getTransaction().commit();


    }


    public void onFinish(ITestContext context) {
        injector.getInstance(EntityManager.class).close();
        injector.getInstance(PersistenceService.class).shutdown();
    }


}
