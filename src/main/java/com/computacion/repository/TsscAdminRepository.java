package com.computacion.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscGame;



@Repository
public interface TsscAdminRepository extends CrudRepository<TsscAdmin,Long>{

	Optional<TsscAdmin> findByUser(String user);
}
