package com.backendecommerce.backendecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backendecommerce.backendecommerce.models.entities.User;

public interface IUserDao extends JpaRepository<User,Long>{
    
}



