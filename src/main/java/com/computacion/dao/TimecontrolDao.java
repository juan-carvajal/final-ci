package com.computacion.dao;

import java.util.List;

import com.computacion.model.TsscTimecontrol;

public interface TimecontrolDao {
	
	public TsscTimecontrol save(TsscTimecontrol tc);
	public TsscTimecontrol update(TsscTimecontrol tc);
	public void delete(TsscTimecontrol tc);
	public List<TsscTimecontrol> findAll();
	public void removeAll();
	public TsscTimecontrol findById(long id);

}
