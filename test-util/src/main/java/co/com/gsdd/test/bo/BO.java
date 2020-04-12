package co.com.gsdd.test.bo;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.gsdd.test.utils.GenericDAO;
import lombok.Getter;

@Getter
public class BO {

	private GenericDAO dao;

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void inicializar() {
		dao = new GenericDAO(em);
	}

}
