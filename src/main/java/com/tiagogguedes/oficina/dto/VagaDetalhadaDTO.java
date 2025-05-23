package com.tiagogguedes.oficina.dto;

import java.util.ArrayList;
import java.util.List;

import com.tiagogguedes.oficina.entities.Vaga;

public class VagaDetalhadaDTO {
    private Long id;
    private String nome;
    private String status;
    private List<ServicoDTO> servicos = new ArrayList<>();
    
    public VagaDetalhadaDTO() {
    }
    
    public VagaDetalhadaDTO(Vaga vaga) {
        this.id = vaga.getId();
        this.nome = vaga.getNome();
        // O status da vaga pode ser derivado dos serviços ativos
        this.status = determinarStatus(vaga);
    }
    
    private String determinarStatus(Vaga vaga) {
        // Lógica para determinar o status da vaga com base nos serviços
        // Por exemplo, se houver algum serviço ativo, a vaga está "Ocupado"
        return "Livre"; // Valor padrão, deve ser ajustado com base na lógica de negócio
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ServicoDTO> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoDTO> servicos) {
        this.servicos = servicos;
    }
    
    public void addServico(ServicoDTO servico) {
        this.servicos.add(servico);
    }
}
