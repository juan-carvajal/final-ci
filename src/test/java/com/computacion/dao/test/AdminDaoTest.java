package com.computacion.dao.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.computacion.dao.AdminDao;
import com.computacion.model.TsscAdmin;
import com.computacion.model.exceptions.TsscGameException;

@SpringBootTest
//@ContextConfiguration("/applicationContext.xml")
@Rollback(true)
public class AdminDaoTest {

	@Autowired
	public AdminDao adminDao;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAndGet() {
		TsscAdmin admin = new TsscAdmin();
		TsscAdmin adminSaved = adminDao.save(admin);
		Assertions.assertTrue(admin == adminSaved);
		Assertions.assertTrue(adminSaved.getId() != 0);
		Assertions.assertTrue(adminDao.getById(adminSaved.getId()) != null);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestUpdate() {
		TsscAdmin admin = new TsscAdmin();
		admin = adminDao.save(admin);
		admin.setUser("User1");
		adminDao.update(admin);
		admin = adminDao.getById(admin.getId());
		Assertions.assertTrue(admin.getUser().equals("User1"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestGetByUserName() {
		TsscAdmin admin = new TsscAdmin();
		admin.setUser("User1");
		admin = adminDao.save(admin);
		admin = adminDao.getByUsername("User1");
		Assertions.assertTrue(admin.getUser().equals("User1"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void TestDelete() {
		TsscAdmin admin = new TsscAdmin();
		admin.setUser("User1");
		admin = adminDao.save(admin);
		adminDao.delete(admin);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {

			System.out.println(adminDao.getByUsername("User1"));
		});

	}

}
