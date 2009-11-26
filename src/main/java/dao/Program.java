package dao;

import com.wideplay.warp.persist.dao.Finder;

import java.util.HashSet;
import java.util.Set;

/**
 * Here will be listed all queries on one specific entity group
 * 
 * Date: Nov 23, 2009
 */
public interface Program {
    @Finder(query = "from Program", returnAs = HashSet.class)
    public Set<entity.Program> findAll();

}

