package com.computacion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscGame;

@Repository
@Scope("singleton")
public class AdminDatoImpl implements AdminDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public TsscAdmin getById(long id) {
		return em.find(TsscAdmin.class, id);
	}

	@Override
	public TsscAdmin getByUsername(String username) {
		TypedQuery<TsscAdmin>q=em.createQuery("select a from TsscAdmin a where a.user=:user", TsscAdmin.class);
		return 	q.setParameter("user", username).getSingleResult();
	}

	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "Select a from TsscAdmin a";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public TsscAdmin save(TsscAdmin admin) {
		em.persist(admin);
		em.flush();
		return admin;
	}

	@Override
	public TsscAdmin update(TsscAdmin admin) {
		em.merge(admin);
		return admin;
	}

	@Override
	public void delete(TsscAdmin admin) {
		em.remove(admin);
	}

	@Override
	public void removeAll() {
		em.createQuery("delete from TsscAdmin").executeUpdate();
		
	}

}
