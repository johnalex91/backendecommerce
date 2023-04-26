package com.backendecommerce.backendecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.backendecommerce.backendecommerce.models.entities.Product;;
@Service
public interface IProductService {
    
    public List<Product> show();
    public Product findById(Long id);
	public Product save(Product cliente);
	public void delete(Long id);
    public void buy(Long id);

}
