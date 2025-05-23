package com.tiagogguedes.oficina.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogguedes.oficina.entities.User;
import com.tiagogguedes.oficina.services.UserService;

//um controlador RESTful, indica que os métodos da classe retornam dados 
//diretamente em formato JSON ou XML, sem renderizar uma página HTML.
@RestController
//usada para mapear solicitações HTTP
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	//é um atalho para @RequestMapping(method = RequestMethod.GET) e é usada especificamente 
	//para mapear solicitações HTTP GET
	@GetMapping
	public ResponseEntity<List<User>>findAll(){
		
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	
	//@PathVariable para que o metódo reconheça a passagem do id
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
	
	

}
