package dao;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;
import entity.Employee;
import entity.Program;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Here will be listed all queries on one specific entity group
 * 
 * Date: Nov 23, 2009
 */
public interface ProgramQ {
    @Finder(query = "from Program", returnAs = HashSet.class)
    public Set<Program> findAll();

}

