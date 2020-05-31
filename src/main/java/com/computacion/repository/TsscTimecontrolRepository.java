package com.computacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscTimecontrol;


@Repository
public interface TsscTimecontrolRepository extends CrudRepository<TsscTimecontrol,Long> {

}
