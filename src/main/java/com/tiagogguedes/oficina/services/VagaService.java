package com.tiagogguedes.oficina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tiagogguedes.oficina.entities.Vaga;
import com.tiagogguedes.oficina.repositories.VagaRepository;
import com.tiagogguedes.oficina.services.exceptions.ResourceNotFoundException;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    public List<Vaga> findAll() {
        return repository.findAll();
    }

    public Vaga findById(Long id) {
        Optional<Vaga> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Vaga insert(Vaga obj) {
        return repository.save(obj);
    }
    
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    
    public Vaga update(Long id, Vaga obj) {
        try {
            Vaga entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Vaga entity, Vaga obj) {
        entity.setNome(obj.getNome());
    }
}