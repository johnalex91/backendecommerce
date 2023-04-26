package com.backendecommerce.backendecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendecommerce.backendecommerce.services.IUserService;
import com.backendecommerce.backendecommerce.models.entities.User;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping("/")
	public List<User> index() {
        
		return service.show();
	}    
    
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            service.delete(id);
            response.put("data", "User Eliminado con exito");			
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}  
    
    

}
