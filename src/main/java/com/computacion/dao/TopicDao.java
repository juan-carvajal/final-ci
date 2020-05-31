package com.computacion.dao;

import java.time.LocalDate;
import java.util.List;

import com.computacion.model.TsscTopic;

public interface TopicDao {
	
	
	public TsscTopic save(TsscTopic topic);
	public TsscTopic update(TsscTopic topic);
	public List<TsscTopic> findAll();
	public TsscTopic findByName(String name);
	public TsscTopic findbyDescription(String desc);
	public void delete(TsscTopic topic);
	public List<Object[]> listTopicWithGamesCountByDate(LocalDate date);
	public void removeAll();
	public TsscTopic findById(long id);

}
