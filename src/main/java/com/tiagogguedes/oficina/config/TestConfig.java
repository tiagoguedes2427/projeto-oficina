package com.tiagogguedes.oficina.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tiagogguedes.oficina.entities.User;
import com.tiagogguedes.oficina.repositories.UserRepository;


//Classe auxiliar para algumas configurações exclusiva para o
//perfil de teste


@Configuration
//Essa anotação tem que ser igual ao arguivo application.properties
//profiles test

//Implementou o CommandLineRunner para adicionar no
//banco de dados ja ao iniciar a aplicação

@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	//injeção de dependências 
	@Autowired
	private UserRepository userRepository;

	//Metodo funciona desde o início da aplicaççao
	//e ja adiciona no banco de dados
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
