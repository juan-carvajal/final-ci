package com.computacion.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscGame;

@Repository
@Scope("singleton")
public class GameDaoImpl implements GameDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TsscGame> findByTopicName(String name) {
//	    TypedQuery<Country> query = em.createQuery(
//	            "SELECT c FROM Country c WHERE c.name = :name", Country.class);
//	        return query.setParameter("name", name).getSingleResult();
		TypedQuery<TsscGame>q=em.createQuery("select g from TsscGame g where g.tsscTopic.name=:name", TsscGame.class);
		return 	q.setParameter("name", name).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicDescription(String desc) {
		TypedQuery<TsscGame>q=em.createQuery("select g from TsscGame g where g.tsscTopic.description=:desc", TsscGame.class);
		return 	q.setParameter("desc", desc).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicId(long topicId) {
		TypedQuery<TsscGame>q=em.createQuery("select g from TsscGame g where g.tsscTopic.id=:id", TsscGame.class);
		return 	q.setParameter("id", topicId).getResultList();
	}

	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select g from TsscGame g";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public TsscGame save(TsscGame game) {
		em.persist(game);
		em.flush();
		return game;
	}

	@Override
	public TsscGame update(TsscGame game) {
em.merge(game);
return game;
	}

	@Override
	public List<TsscGame> findByDateRange(LocalDate lower, LocalDate upper) {
		TypedQuery<TsscGame>q=em.createQuery("select g from TsscGame g where g.scheduledDate between :date1 and :date2", TsscGame.class);
		return 	q.setParameter("date1", lower).setParameter("date2", upper).getResultList();
	}

	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate date, LocalTime lower,LocalTime upper) {
		TypedQuery<TsscGame>q=em.createQuery("select g from TsscGame g where g.scheduledDate=:date and g.scheduledTime between :time1 and :time2", TsscGame.class);
		return 	q.setParameter("time1", lower).setParameter("time2", upper).setParameter("date", date).getResultList();
	}
	
	

	@Override
	public void delete(TsscGame game) {
em.remove(game);
		
	}

	@Override
	public List<TsscGame> gamesExtraQuery(LocalDate date) {
		String jpql = "select a from TsscGame a where a.scheduledDate =:date and (size(a.tsscStories)<10 OR size(a.tsscTimecontrols) = 0)";
		Query q = em.createQuery(jpql);
		q.setParameter("date", date);
		List<TsscGame> results = q.getResultList();
		return results;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		em.createQuery("delete from TsscGame").executeUpdate();
	}

	@Override
	public TsscGame findById(long id) {
		// TODO Auto-generated method stub
		return em.find(TsscGame.class,id);
	}

}
