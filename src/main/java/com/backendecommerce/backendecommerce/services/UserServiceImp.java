package com.backendecommerce.backendecommerce.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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

	/*
	@Override
	public List<Product> show() {
		String hql = "SELECT c FROM Product c";
		return (List<Product>)entitymanager.createQuery(hql)
				.getResultList();
	}
	*/

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
	public User save(User product) {
		return dao.save(product);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
