package com.computacion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TimecontrolDaoImpl implements TimecontrolDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public TsscTimecontrol save(TsscTimecontrol tc) {
em.persist(tc);
em.flush();
return tc;

	}

	@Override
	public TsscTimecontrol update(TsscTimecontrol tc) {
em.merge(tc);
return tc;
	}

	@Override
	public void delete(TsscTimecontrol tc) {
		// TODO Auto-generated method stub
		em.remove(tc);
		
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "Select tc from TsscTimecontrol tc";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		em.createQuery("delete from TsscTimecontrol").executeUpdate();
	}

	@Override
	public TsscTimecontrol findById(long id) {
		// TODO Auto-generated method stub
		return em.find(TsscTimecontrol.class, id);
	}

}
