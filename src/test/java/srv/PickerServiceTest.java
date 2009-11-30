package srv;

import entity.Bureau;
import entity.Recipient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Date: Nov 29, 2009
 */
public class PickerServiceTest extends DBBootStrapper {
    private PickerService srv;

    @BeforeTest
    public void preTest() {
        srv = injector.getInstance(PickerService.class);
    }

    @Test
    public void listAllMunicipalities() {
        Set<Bureau> bureaus = srv.listAllBureaus();
        assertEquals(1, bureaus.size());
    }

    @Test
    public void testToString() {
        Recipient recipient = new Recipient();
        String defaultJavaImplementation = Recipient.class.getName() + "@" + Integer.toHexString(recipient.hashCode());
        assertEquals(defaultJavaImplementation, recipient.toString());

    }

}
