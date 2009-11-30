package srv;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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


        try {
            if (program.getId() != null && program.getId() > 0) {
                program.setModifiedDate(new Date());
                program = em.merge(program);
            } else {
                program.setId(null);
                program.setCreationDate(new Date());
                em.persist(program);
            }
        } catch (PersistenceException pe) {
            //todo log into tomcat
            pe.printStackTrace();

        }

        return program;


    }


}
