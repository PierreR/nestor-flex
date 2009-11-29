package srv;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import dao.Utils;
import entity.Municipality;
import entity.Program;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Set;

/**
 * The service layer : should roughly match user stories
 * Date: Nov 18, 2009
 */
public class ProgramService  {

    @Inject
    Provider<EntityManager> emp;

    @Inject
    dao.Program dao;

    @Inject
    Utils utils;

    public Set<entity.Program> listAll() {
        return dao.listAll();
    }

    public entity.Program findByName(String name) {
        return dao.findByName(name);
    }



    @Transactional
    public Program save(Program program) {
        EntityManager em = emp.get();

        if (program.getId() != null && program.getId() > 0) {
            program.setModifiedDate(new Date());
            program = em.merge(program);
        } else {
            // this is a hack because BlazeDS cannot pass null Number
            program.setId(null);
            program.setCreationDate(new Date());
            em.persist(program);
        }

        return program;


    }
}
