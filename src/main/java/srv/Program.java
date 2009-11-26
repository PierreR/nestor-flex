package srv;


import com.google.inject.Inject;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * The service layer : should roughly match user stories
 * Date: Nov 18, 2009
 */
public class Program {

    @Inject
    dao.Program dao;

    @Inject
    EntityManager em;



    public int save(entity.Program program) {
        //validate
        // create
        em.persist(program);
        return program.getId();
    }

    public Set<entity.Program> findAll() {
        return dao.findAll();
    }
}
