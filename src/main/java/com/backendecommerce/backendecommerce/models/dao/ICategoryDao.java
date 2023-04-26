package com.backendecommerce.backendecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backendecommerce.backendecommerce.models.entities.Category;

public interface ICategoryDao extends JpaRepository<Category,Long>{
    
}
