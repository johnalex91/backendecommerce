package com.backendecommerce.backendecommerce.services;

import org.springframework.stereotype.Service;
import com.backendecommerce.backendecommerce.models.entities.Category;
import java.util.List;

@Service
public interface ICategoryService {
  
    public List<Category> show();
    public Category findById(Long id);
	public Category save(Category cliente);
	public void delete(Long id);

}
