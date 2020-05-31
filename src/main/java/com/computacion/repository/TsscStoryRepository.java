package com.computacion.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscGame;
import com.computacion.model.TsscStory;

@Repository
public interface TsscStoryRepository extends CrudRepository<TsscStory,Long> {

	
	public List<TsscStory> findAllByTsscGame(TsscGame game);
}
