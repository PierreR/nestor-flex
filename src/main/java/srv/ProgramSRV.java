package srv;


import com.google.inject.Inject;
import dao.ProgramQ;
import entity.Program;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * The service layer : should roughly match user stories
 * Date: Nov 18, 2009
 */
public class ProgramSRV {

    @Inject
    ProgramQ dao;

    @Inject
    EntityManager em;



    public int create(Program program) {
        //validate
        // create
        em.persist(program);
        return program.getId();
    }

    public Set<Program> findAll() {
        return dao.findAll();
    }
}
