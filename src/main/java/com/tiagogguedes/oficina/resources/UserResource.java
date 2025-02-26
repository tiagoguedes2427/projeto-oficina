package com.tiagogguedes.oficina.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogguedes.oficina.entities.User;

//um controlador RESTful, indica que os métodos da classe retornam dados 
//diretamente em formato JSON ou XML, sem renderizar uma página HTML.
@RestController
//usada para mapear solicitações HTTP
@RequestMapping(value = "/users")
public class UserResource {
	
	//é um atalho para @RequestMapping(method = RequestMethod.GET) e é usada especificamente 
	//para mapear solicitações HTTP GET
	@GetMapping
	public ResponseEntity<User> findAll(){
		
		User u = new User(1L, "Tiago", "tiago@gmail.com", "888888", "1234");
		return ResponseEntity.ok().body(u);
	}
	
	
	
	
	
	

}
