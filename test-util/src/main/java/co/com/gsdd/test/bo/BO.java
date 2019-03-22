package co.com.gsdd.test.bo;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.gsdd.test.utils.GenericDAO;

public class BO {

	protected GenericDAO dao;

	@PersistenceContext
	protected EntityManager em;

	@PostConstruct
	public void inicializar() {
		dao = new GenericDAO(em);
	}

	public GenericDAO getDao() {
		return dao;
	}

	public EntityManager getEm() {
		return em;
	}

}
