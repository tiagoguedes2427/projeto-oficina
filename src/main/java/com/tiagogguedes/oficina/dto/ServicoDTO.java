package com.tiagogguedes.oficina.dto;

import java.util.ArrayList;
import java.util.List;

import com.tiagogguedes.oficina.entities.Colaborador;
import com.tiagogguedes.oficina.entities.Servico;

public class ServicoDTO {
    private Long id;
    private String descricao;
    private String dataHora;
    private String status;
    private Long supervisorId;
    private String supervisorNome;
    private Long vagaId;
    private String vagaNome;
    private List<ColaboradorDTO> colaboradores = new ArrayList<>();
    
    public ServicoDTO() {
    }
    
    public ServicoDTO(Servico servico) {
        this.id = servico.getId();
        this.descricao = servico.getDescricao();
        this.dataHora = servico.getDataHora() != null ? servico.getDataHora().toString() : null;
        this.status = servico.getStatus();
        
        if (servico.getSupervisor() != null) {
            this.supervisorId = servico.getSupervisor().getId();
            this.supervisorNome = servico.getSupervisor().getName();
        }
        
        if (servico.getVaga() != null) {
            this.vagaId = servico.getVaga().getId();
            this.vagaNome = servico.getVaga().getNome();
        }
        
        // Converter colaboradores para DTOs
        for (Colaborador colaborador : servico.getColaboradores()) {
            this.colaboradores.add(new ColaboradorDTO(colaborador));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorNome() {
        return supervisorNome;
    }

    public void setSupervisorNome(String supervisorNome) {
        this.supervisorNome = supervisorNome;
    }

    public Long getVagaId() {
        return vagaId;
    }

    public void setVagaId(Long vagaId) {
        this.vagaId = vagaId;
    }

    public String getVagaNome() {
        return vagaNome;
    }

    public void setVagaNome(String vagaNome) {
        this.vagaNome = vagaNome;
    }

    public List<ColaboradorDTO> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<ColaboradorDTO> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
