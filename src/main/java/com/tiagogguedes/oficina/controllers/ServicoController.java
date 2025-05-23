package com.tiagogguedes.oficina.controllers;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiagogguedes.oficina.entities.Colaborador;
import com.tiagogguedes.oficina.entities.Servico;
import com.tiagogguedes.oficina.services.ColaboradorService;
import com.tiagogguedes.oficina.services.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {
	
	@Autowired
	private ServicoService service;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping
	public ResponseEntity<List<Servico>>findAll(){
		List<Servico> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Servico> findById(@PathVariable Long id){
		Servico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Servico> insert(@RequestBody Servico obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/colaboradores")
	public ResponseEntity<Set<Colaborador>> findColaboradores(@PathVariable Long id) {
		Servico servico = service.findById(id);
		return ResponseEntity.ok().body(servico.getColaboradores());
	}
	
	@PostMapping(value = "/{servicoId}/colaboradores/{colaboradorId}")
	public ResponseEntity<Void> addColaborador(@PathVariable Long servicoId, @PathVariable Long colaboradorId) {
		Servico servico = service.findById(servicoId);
		Colaborador colaborador = colaboradorService.findById(colaboradorId);
		servico.addColaborador(colaborador);
		service.update(servicoId, servico);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{servicoId}/colaboradores/{colaboradorId}")
	public ResponseEntity<Void> removeColaborador(@PathVariable Long servicoId, @PathVariable Long colaboradorId) {
		Servico servico = service.findById(servicoId);
		Colaborador colaborador = colaboradorService.findById(colaboradorId);
		servico.removeColaborador(colaborador);
		service.update(servicoId, servico);
		return ResponseEntity.noContent().build();
	}
}
