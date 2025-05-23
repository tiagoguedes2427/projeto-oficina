package com.tiagogguedes.oficina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogguedes.oficina.entities.User;
import com.tiagogguedes.oficina.resources.LoginRequest;
import com.tiagogguedes.oficina.resources.RecuperarSenhaRequest;
import com.tiagogguedes.oficina.resources.RedefinirSenhaRequest;
import com.tiagogguedes.oficina.services.AuthService;

@RestController
@RequestMapping(value = "/auth" )
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<String> recuperarSenha(@RequestBody RecuperarSenhaRequest request) {
        boolean enviado = authService.enviarRecuperacaoSenha(request.getEmail());
        if (enviado) {
            return ResponseEntity.ok("Email de recuperação enviado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Não foi possível enviar o email de recuperação");
        }
    }
    
    
    @PostMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@RequestBody RedefinirSenhaRequest request) {
        boolean sucesso = authService.redefinirSenha(
            request.getEmail(), 
            request.getToken(), 
            request.getNovaSenha()
        );
        
        if (sucesso) {
            return ResponseEntity.ok("Senha redefinida com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Token inválido ou expirado");
        }
    }

}
