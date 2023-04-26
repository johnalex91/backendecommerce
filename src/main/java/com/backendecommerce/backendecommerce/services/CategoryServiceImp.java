package com.backendecommerce.backendecommerce.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.backendecommerce.backendecommerce.models.entities.Category;
import com.backendecommerce.backendecommerce.models.dao.ICategoryDao;

@Service
public class CategoryServiceImp implements ICategoryService{
    
    @PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	private ICategoryDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Category> show() {
		return (List<Category>) dao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Category findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Category save(Category category) {
		return dao.save(category);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
