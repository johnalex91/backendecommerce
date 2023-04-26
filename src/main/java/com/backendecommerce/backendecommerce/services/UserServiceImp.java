package com.backendecommerce.backendecommerce.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendecommerce.backendecommerce.models.entities.User;
import com.backendecommerce.backendecommerce.models.dao.IUserDao;

@Service
public class UserServiceImp implements IUserService{
   
    @PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	private IUserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<User>show() {
		return (List<User>) dao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return dao.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByUsername(String username) {
		String hql = "select u from User u where u.username= '"+username+"'";
		return (List<User>)entitymanager.createQuery(hql)
				.getResultList();
	}	

	@Override
	@Transactional(readOnly = true)
	public List<User> findByEmail(String email) {
		String hql = "select u from User u where u.email= '"+email+"'";
		return (List<User>)entitymanager.createQuery(hql)
				.getResultList();
	}		

}
