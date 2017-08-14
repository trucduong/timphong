package service.auth.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import core.dao.dao.IDao;

@Repository
public class AuthDao implements IDao {
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	@Override
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public EntityManager getEm() {
		return this.em;
	}

}
