package com.backendecommerce.backendecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendecommerce.backendecommerce.services.ICategoryService;
import com.backendecommerce.backendecommerce.models.entities.Category;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    

	@Autowired
	private ICategoryService service;

	@GetMapping("/")
	public List<Category> index() {
		return service.show();
	}



}
