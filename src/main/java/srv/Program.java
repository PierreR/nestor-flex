package srv;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Set;

/**
 * The service layer : should roughly match user stories
 * Date: Nov 18, 2009
 */
public class Program {

    @Inject
    dao.Program dao;

    @Inject
    Provider<EntityManager> emp;


    @Transactional
    public entity.Program save(entity.Program program) {
        //validate
        // create

        EntityManager em = emp.get();

        if (program.getId() != null) {
            program.setModifiedDate(new Date());
            program = em.merge(program);
        } else {
            program.setCreationDate(new Date());
        }

        em.persist(program);

        return program;
    }

    public Set<entity.Program> findAll() {
        return dao.findAll();
    }

    public entity.Program findByName(String name) {
        return dao.findByName(name);
    }
}
