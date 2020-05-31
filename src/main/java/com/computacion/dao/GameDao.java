package com.computacion.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.computacion.model.TsscGame;

public interface GameDao {
	
	
	public List<TsscGame> findByTopicName(String topicName);
	public List<TsscGame> findByTopicDescription(String topicDesc);
	public List<TsscGame> findByTopicId(long topicId);
	public List<TsscGame> findAll();
	public TsscGame save(TsscGame game);
	public TsscGame update(TsscGame game);
	public List<TsscGame> findByDateRange(LocalDate lower, LocalDate upper);
	public List<TsscGame> findByDateAndTimeRange(LocalDate date, LocalTime lower,LocalTime upper);
	public void delete(TsscGame game);
	public List<TsscGame> gamesExtraQuery(LocalDate date);
	public void removeAll();
	public TsscGame findById(long id);

}
