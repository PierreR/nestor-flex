package dao;

import com.wideplay.warp.persist.dao.Finder;
import entity.Municipality;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: Nov 28, 2009
 */
public interface Utils {
    
    @Finder(query = "from Municipality", returnAs = HashSet.class)
    public Set<Municipality> listAllMunicipalities();

}
