package srv;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import entity.BaseEntity;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Date: Nov 29, 2009
 */
public abstract class Service<E extends BaseEntity> {

    @Inject
    Provider<EntityManager> emp;
    
    @Transactional
    public E save(E program) {
        EntityManager em = emp.get();

        if (program.getId() != null) {
            program.setModifiedDate(new Date());
            program = em.merge(program);
        } else {
            program.setCreationDate(new Date());
            em.persist(program);
        }
    
        return program;


    }
}
