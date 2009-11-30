package Utils;

import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import static org.testng.AssertJUnit.*;

/**
 * Date: Nov 30, 2009
 */

public class StringUtils {

    public static boolean isEmpty(String txt) {
        return txt == null || txt.isEmpty();
    }

    @Test public void isEmpty() {
       assertTrue(StringUtils.isEmpty(null));
       assertTrue(StringUtils.isEmpty(""));
       assertFalse(StringUtils.isEmpty("c"));
    }


}
