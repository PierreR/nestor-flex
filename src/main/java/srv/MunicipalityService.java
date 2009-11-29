package srv;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import dao.Utils;
import entity.Municipality;

import java.util.Set;

/**
 * Date: Nov 29, 2009
 */
public class MunicipalityService extends Service<Municipality> {
    @Inject
    Utils dao;


    public Set<Municipality> listAllMunicipalities() {
        return dao.listAllMunicipalities();
    }


}
