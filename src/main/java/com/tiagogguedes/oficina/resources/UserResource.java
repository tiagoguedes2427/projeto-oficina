package com.tiagogguedes.oficina.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogguedes.oficina.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		
		User u = new User(1L, "Tiago", "tiago@gmail.com", "888888", "1234");
		return ResponseEntity.ok().body(u);
	}
	
	
	
	
	
	

}
