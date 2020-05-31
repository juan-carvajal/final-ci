package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscGroup;

@Repository
public interface TsscGroupRepository extends CrudRepository<TsscGroup,Long>{
	
}
