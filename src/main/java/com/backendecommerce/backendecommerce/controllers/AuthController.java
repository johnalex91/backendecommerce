package com.backendecommerce.backendecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backendecommerce.backendecommerce.models.entities.User;
import com.backendecommerce.backendecommerce.models.entities.UserLogin;
import com.backendecommerce.backendecommerce.services.IUserService;

@CrossOrigin(origins = { "*", "*" })
@RestController
@RequestMapping("/auth")
public class AuthController {
    
	@Autowired
	private IUserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;    

    @PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> authlogin(@RequestBody UserLogin obj) throws Exception{
        Map<String,Object> response = new HashMap<>();
		try {		
            List<User> validUsername = service.findByUsername(obj.getUsername());

            if(validUsername.size()>0){
                System.out.print(obj.getPassword());
                if (passwordEncoder.matches(obj.getPassword(), validUsername.get(0).getPassword())) {
                    response.put("success", "OK");			
                    response.put("token", "123456789");			
                    response.put("username", obj.getUsername());			
                    response.put("role", validUsername.get(0).getRole());			
                } else {
                    response.put("success", "ERROR");			               }
               // String pass = passwordEncoder.
                
            }else{
                response.put("success", "ERROR");			
            }
		}catch(NumberFormatException e) {
			System.out.println("Error Number Format "+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Error nm pointer "+e.getMessage());
		}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	} 

}
