package com.computacion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscStory;

@Repository
@Scope("singleton")
public class StoryDaoImpl implements StoryDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public TsscStory save(TsscStory story) {
		em.persist(story);
		em.flush();
		return story;
	}

	@Override
	public TsscStory update(TsscStory story) {
em.merge(story);
return story;
	}

	@Override
	public void delete(TsscStory story) {
em.remove(story);
		
	}

	@Override
	public List<TsscStory> findAll() {
		String jpql = "Select s from TsscStory s";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		em.createQuery("delete from TsscStory").executeUpdate();
	}

	@Override
	public TsscStory findById(long id) {
		return em.find(TsscStory.class, id);
	}

}
