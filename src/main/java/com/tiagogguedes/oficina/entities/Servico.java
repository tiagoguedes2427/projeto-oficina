package com.tiagogguedes.oficina.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Servico implements Serializable{
	private static final long serialVercionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private LocalDateTime dataHora;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "supervisor_id")
	private User supervisor;
	
	@ManyToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;
	
	@ManyToMany
	@JoinTable(
		name = "tb_servico_colaborador",
		joinColumns = @JoinColumn(name = "servico_id"),
		inverseJoinColumns = @JoinColumn(name = "colaborador_id")
	)
	private Set<Colaborador> colaboradores = new HashSet<>();
	
	public Servico() {
		// Construtor vazio
	}
	
	public Servico(Long id, String descricao, LocalDateTime dataHora, String status, User supervisor, Vaga vaga) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.status = status;
		this.supervisor = supervisor;
		this.vaga = vaga;
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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}
	
	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	
	public Set<Colaborador> getColaboradores() {
		return colaboradores;
	}
	
	public void addColaborador(Colaborador colaborador) {
		this.colaboradores.add(colaborador);
	}
	
	public void removeColaborador(Colaborador colaborador) {
		this.colaboradores.remove(colaborador);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
