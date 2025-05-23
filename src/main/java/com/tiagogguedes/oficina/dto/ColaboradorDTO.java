package com.tiagogguedes.oficina.dto;

import com.tiagogguedes.oficina.entities.Colaborador;

public class ColaboradorDTO {
    private Long id;
    private String nome;
    private String funcao;
    private String matricula;
    private String telefone;
    private String email;
    private Boolean ativo;
    
    public ColaboradorDTO() {
    }
    
    public ColaboradorDTO(Colaborador colaborador) {
        this.id = colaborador.getId();
        this.nome = colaborador.getNome();
        this.funcao = colaborador.getFuncao();
        this.matricula = colaborador.getMatricula();
        this.telefone = colaborador.getTelefone();
        this.email = colaborador.getEmail();
        this.ativo = colaborador.getAtivo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
