package com.computacion.dao;

import java.util.List;

import com.computacion.model.TsscAdmin;

public interface AdminDao {
	
	
	public TsscAdmin getById(long id);
	public TsscAdmin getByUsername(String username);
	public List<TsscAdmin> findAll();
	public TsscAdmin save(TsscAdmin admin);
	public TsscAdmin update(TsscAdmin admin);
	public void delete(TsscAdmin admin);
	public void removeAll();

}
