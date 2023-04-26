package com.backendecommerce.backendecommerce.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.backendecommerce.backendecommerce.models.entities.Product;
import com.backendecommerce.backendecommerce.models.dao.IProductDao;
@Service
public class ProductServiceImp implements IProductService{
   
    @PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	private IProductDao dao;

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
	public List<Product> show() {
		return (List<Product>) dao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return dao.save(product);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public void buy(Long id) {
		dao.deleteById(id);
	}	

}
