package com.backendecommerce.backendecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backendecommerce.backendecommerce.models.entities.Product;

public interface IProductDao extends JpaRepository<Product,Long>{
    
}
