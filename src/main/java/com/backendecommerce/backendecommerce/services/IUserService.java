package com.backendecommerce.backendecommerce.services;

import java.util.List;

import com.backendecommerce.backendecommerce.models.entities.User;

public interface IUserService {
   
    public List<User>show();
    public User findById(Long id);
	public User save(User cliente);
	public void delete(Long id);


}
