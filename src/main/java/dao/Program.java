package dao;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;
import entity.Municipality;

import java.util.HashSet;
import java.util.Set;

/**
 * Here will be listed all queries on one specific entity group
 * <p/>
 * Date: Nov 23, 2009
 */
public interface Program {

    @Finder(query = "from Program", returnAs = HashSet.class)
    public Set<entity.Program> listAll();
    
    @Finder(query = "select p from Program p where p.municipality.name=:name")
    public entity.Program findByMunipality(@Named("name") String name);

    @Finder(query = "from Program p where p.name=:name")
    public entity.Program findByName(@Named("name") String name);
}

