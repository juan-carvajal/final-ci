package com.computacion.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscTopic;

@Repository
@Scope("singleton")
public class TopicDaoImpl implements TopicDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public TsscTopic save(TsscTopic topic) {
		em.persist(topic);
		em.flush();
		return topic;
	}

	@Override
	public TsscTopic update(TsscTopic topic) {
		// TODO Auto-generated method stub
		em.merge(topic);
		return topic;
	}

	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select t from TsscTopic t";
		return 	em.createQuery(jpql).getResultList();
	}

	@Override
	public TsscTopic findByName(String name) {
		TypedQuery<TsscTopic>q=em.createQuery("select t from TsscTopic t where t.name=:name", TsscTopic.class);
		return 	q.setParameter("name", name).getSingleResult();
	}

	@Override
	public TsscTopic findbyDescription(String desc) {
		TypedQuery<TsscTopic>q=em.createQuery("select t from TsscTopic t where t.description=:desc", TsscTopic.class);
		return 	q.setParameter("desc", desc).getSingleResult();
	}

	@Override
	public void delete(TsscTopic topic) {
		em.remove(topic);
		
	}

	@Override
	public List<Object[]> listTopicWithGamesCountByDate(LocalDate date) {
		String jpql = "select b.tsscTopic , count(b.tsscTopic) from TsscGame b where b.id in (select a.id from TsscGame a where a.scheduledDate = :date order by a.scheduledTime desc) group by b.tsscTopic";
		Query query = em.createQuery(jpql);
		query.setParameter("date", date);
		return query.getResultList();
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		em.createQuery("delete from TsscTopic").executeUpdate();
	}

	@Override
	public TsscTopic findById(long id) {
		// TODO Auto-generated method stub
		return em.find(TsscTopic.class, id);
	}

}
