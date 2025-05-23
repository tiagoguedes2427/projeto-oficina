package com.tiagogguedes.oficina.services;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagogguedes.oficina.entities.User;
import com.tiagogguedes.oficina.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    // Armazenar tokens de recuperação (em produção, use um banco de dados)
    private Map<String, String> tokensRecuperacao = new HashMap<>();

    public User authenticate(String email, String password) {
        // Buscar usuário pelo email
        User user = userRepository.findByEmail(email);
        
        // Verificar se o usuário existe e a senha está correta
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        
        return null;
    }

    public boolean enviarRecuperacaoSenha(String email) {
        // Verificar se o email existe
        User user = userRepository.findByEmail(email);
        
        if (user != null) {
            // Gerar token aleatório de 6 dígitos
            String token = gerarToken();
            
            // Armazenar token (associado ao email)
            tokensRecuperacao.put(email, token);
            
            // Enviar email com o token
            emailService.enviarEmailRecuperacaoSenha(email, token);
            
            return true;
        }
        
        return false;
    }
    
    private String gerarToken() {
        // Gerar token numérico de 6 dígitos
        Random random = new Random();
        int token = 100000 + random.nextInt(900000);
        return String.valueOf(token);
    }
    
    // Método para validar o token e redefinir a senha
    public boolean redefinirSenha(String email, String token, String novaSenha) {
        // Verificar se o token é válido para o email
        String tokenArmazenado = tokensRecuperacao.get(email);
        
        if (tokenArmazenado != null && tokenArmazenado.equals(token)) {
            // Buscar usuário
            User user = userRepository.findByEmail(email);
            
            if (user != null) {
                // Atualizar senha
                user.setPassword(novaSenha);
                userRepository.save(user);
                
                // Remover token usado
                tokensRecuperacao.remove(email);
                
                return true;
            }
        }
        
        return false;
    }
}







/*
package com.tiagogguedes.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagogguedes.oficina.entities.User;
import com.tiagogguedes.oficina.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String password) {
        // Buscar usuário pelo email
        User user = userRepository.findByEmail(email);
        
        // Verificar se o usuário existe e a senha está correta
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        
        return null;
    }

    public boolean enviarRecuperacaoSenha(String email) {
        // Verificar se o email existe
        User user = userRepository.findByEmail(email);
        
        if (user != null) {
            // Aqui você implementaria o envio real de email
            // Para simplificar, apenas retornamos true
            return true;
        }
        
        return false;
    }
    
  }
*/