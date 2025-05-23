package com.tiagogguedes.oficina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tiagogguedes.oficina.entities.Servico;
import com.tiagogguedes.oficina.repositories.ServicoRepository;
import com.tiagogguedes.oficina.services.exceptions.ResourceNotFoundException;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;
    
    public List<Servico> findAll() {
        return repository.findAll();
    }
    
    public Servico findById(Long id) {
        Optional<Servico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Servico insert(Servico obj) {
        return repository.save(obj);
    }
    
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    
    public Servico update(Long id, Servico obj) {
        try {
            Servico entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Servico entity, Servico obj) {
        entity.setDescricao(obj.getDescricao());
        entity.setDataHora(obj.getDataHora());
        entity.setStatus(obj.getStatus());
        entity.setSupervisor(obj.getSupervisor());
        entity.setVaga(obj.getVaga());
    }
}

