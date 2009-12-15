package nestor.srv;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import nestor.entity.BaseEntity;

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
    final public E save(E entity) {

        try {
            if (entity.getId() != null && entity.getId() > 0) {
                entity = update(entity);
            } else {
                create(entity);
            }
        } catch (PersistenceException pe) {
            //todo log into tomcat
            pe.printStackTrace();

        }

        return entity;


    }


    E update(final E entity) {
        EntityManager em = emp.get();
        entity.setModifiedDate(new Date());
        return em.merge(entity);
    }

    void create(final E entity) {
        EntityManager em = emp.get();
        entity.setId(null);
        entity.setCreationDate(new Date());
        entity.setModifiedDate(new Date());

        em.persist(entity);

    }


}
