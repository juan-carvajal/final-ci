package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscGame;


@Repository
public interface TsscGameRepository extends CrudRepository<TsscGame,Long>{
	
}
