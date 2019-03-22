package co.com.gsdd.test.utils;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.gsdd.test.constants.NumericConstants;
import co.com.gsdd.test.entities.AbstractCommonEntity;
import co.com.gsdd.test.enums.EntityState;

@SuppressWarnings({ "unchecked" })
public class GenericDAO implements Serializable {

    private static final long serialVersionUID = 3007335287625452029L;
	private EntityManager entityManager;

	public GenericDAO() {

	}

	public GenericDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void persist(AbstractCommonEntity instancia) {
		entityManager.persist(instancia);
		entityManager.flush();
	}
	
	public <T> T update(AbstractCommonEntity updatedInstance) {
        T data = (T) entityManager.merge(updatedInstance);
        entityManager.flush();
        return data;
    }

	public <T> T find(Class<T> clase, Object pk) {
		return entityManager.find(clase, pk);
	}
	
	public <T extends AbstractCommonEntity> boolean logicalDelete(Class<T> clase, Object pk) {
	    boolean result = false;
	    AbstractCommonEntity instance = find(clase, pk);
	    if (instance != null) {
	        instance.setStatus(EntityState.INACTIVE);
	        update(instance); 
	        result = true;
	    } 
	    return result;
    }
	
	public <T extends AbstractCommonEntity> boolean physicalDelete(Class<T> clase, Object pk) {
        boolean result = false;
        AbstractCommonEntity instance = find(clase, pk);
        if (instance != null) {
            entityManager.remove(instance);
            entityManager.flush();
            result = true;
        } 
        return result;
    }

	public <T> List<T> executeNamedQuery(String nombreNamedQuery,
			Object... params) {
		Query q = entityManager.createNamedQuery(nombreNamedQuery);
		setParamsToQuery(q, params);
		return (List<T>) q.getResultList();
	}
	
	private void setParamsToQuery(Query q, Object... params) {
		if (params != null) {
			for (int i = NumericConstants.ZERO; i < params.length; i++) {
				Object object = params[i];
				q.setParameter(i + NumericConstants.ONE, object);
			}
		}
	}

}
