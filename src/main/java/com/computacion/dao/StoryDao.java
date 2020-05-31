package com.computacion.dao;

import java.util.List;

import com.computacion.model.TsscStory;

public interface StoryDao {

	
	public TsscStory save(TsscStory story);
	public TsscStory update(TsscStory story);
	public void delete(TsscStory story);
	public List<TsscStory> findAll();
	public void removeAll();
	public TsscStory findById(long id);

}
