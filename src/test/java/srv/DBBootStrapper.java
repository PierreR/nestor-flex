package srv;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import com.wideplay.warp.persist.jpa.JpaUnit;
import dao.CompanyQ;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.persistence.EntityManager;
import java.sql.Date;

/**
 * The database is only started once at loading time.
 * To be able to start and clean up the db (arguably this is not required) we need to implement TestNG ITestListener.
 * To make the Listener effective, it has to be added as a TestRunner parameter (-listener srv.DBBootStrapper)
 * I have never found a way to do this with JUnit ...
 *
 * It is the responsability of every sub TestClasses to create its own fixtures (using @BeforeClass)
 *
 * Guice injector is made available at the package level as a static variable.
 *  
 * Date: Nov 25, 2009
 */
public class DBBootStrapper implements ITestListener {


    static Injector injector;
    static final String EXPECTED_NAME = "expected";
    static final Date EXPECTED_DAY_DATE = new Date(System.currentTimeMillis());

    static {
        injector = Guice.createInjector(PersistenceService.usingJpa()
                .across(UnitOfWork.TRANSACTION)
             //   .forAll(Matchers.any())
                .addAccessor(dao.Program.class)
                .addAccessor(CompanyQ.class)
                .buildModule(),
                new AbstractModule() {
                    protected void configure() {
                        bindConstant().annotatedWith(JpaUnit.class).to("test");
                    }
                });
    }

    public void onTestStart(ITestResult result) { }

    public void onTestSuccess(ITestResult result) { }

    public void onTestFailure(ITestResult result) { }

    public void onTestSkipped(ITestResult result) { }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    public void onStart(ITestContext context) {
        injector.getInstance(PersistenceService.class).start();
    }

    public void onFinish(ITestContext context) {
        injector.getInstance(EntityManager.class).close();
        injector.getInstance(PersistenceService.class).shutdown();
    }
}
