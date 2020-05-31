package com.computacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computacion.dao.AdminDao;
import com.computacion.model.TsscAdmin;
import com.computacion.repository.TsscAdminRepository;

@Service
public class TsscAdminServiceImpl implements TsscAdminService{
	
	@Autowired
	public AdminDao adminDao;

	@Override
	public TsscAdmin get(long id) throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.adminDao.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public TsscAdmin create(TsscAdmin admin) {
		return this.adminDao.save(admin);
	}

	@Override
	public TsscAdmin update(TsscAdmin admin) {
		// TODO Auto-generated method stub
		return this.adminDao.save(admin);
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		this.adminDao.getById(id);
		this.adminDao.delete(this.adminDao.getById(id));
		
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		this.adminDao.removeAll();
	}

	@Override
	public TsscAdmin get(String user) {
		// TODO Auto-generated method stub
		try {
			return this.adminDao.getByUsername(user);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
