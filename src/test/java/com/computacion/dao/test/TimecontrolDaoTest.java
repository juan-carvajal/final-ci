package com.computacion.dao.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.computacion.dao.GameDao;
import com.computacion.dao.TimecontrolDao;
import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscTimecontrol;

@SpringBootTest
//@ContextConfiguration("/applicationContext.xml")
@Rollback(true)
public class TimecontrolDaoTest {
	
	@Autowired
	public TimecontrolDao tcDao;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAndGet() {
		TsscTimecontrol tc=new TsscTimecontrol();
		tc=tcDao.save(tc);
		
		Assertions.assertTrue(tc.getId()!=0);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		TsscTimecontrol tc=new TsscTimecontrol();
		tc=tcDao.save(tc);
		
		tc.setAutostart("fefefe");
		tcDao.update(tc);
		
		Assertions.assertTrue(tcDao.findById(tc.getId()).getAutostart().equals("fefefe"));
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		TsscTimecontrol tc=new TsscTimecontrol();
		tc=tcDao.save(tc);
		
		tcDao.delete(tc);
		Assertions.assertTrue(tcDao.findById(tc.getId())==null);

	}

}
