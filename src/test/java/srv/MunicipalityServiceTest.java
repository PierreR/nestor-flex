package srv;

import dao.Utils;
import entity.Municipality;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Date: Nov 29, 2009
 */
public class MunicipalityServiceTest extends DBBootStrapper {
    private MunicipalityService srv;
    private Utils dao;

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(MunicipalityService.class);
        dao = injector.getInstance(Utils.class);
    }

    @Test
    public void listAllMunicipalities() {
        Set<Municipality> municipalities = srv.listAllMunicipalities();
        assertEquals(1, municipalities.size());
    }
    
}
