package com.computacion.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.model.TsscAdmin;

public interface TsscAdminService {
	
	
	public TsscAdmin get(long id) throws Exception;
	public TsscAdmin get(String user);
	public TsscAdmin create(TsscAdmin admin);
	public TsscAdmin update(TsscAdmin admin);
	public void remove(long id);
	public void removeAll();

}
