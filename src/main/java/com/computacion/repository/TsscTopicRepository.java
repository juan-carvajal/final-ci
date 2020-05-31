package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscTopic;


@Repository
public interface TsscTopicRepository extends CrudRepository<TsscTopic,Long>{
	
}
