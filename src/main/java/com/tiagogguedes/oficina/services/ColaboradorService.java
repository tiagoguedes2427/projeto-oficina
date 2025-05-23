package com.tiagogguedes.oficina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tiagogguedes.oficina.entities.Colaborador;
import com.tiagogguedes.oficina.repositories.ColaboradorRepository;
import com.tiagogguedes.oficina.services.exceptions.ResourceNotFoundException;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public List<Colaborador> findAll() {
        return repository.findAll();
    }

    public Colaborador findById(Long id) {
        Optional<Colaborador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Colaborador insert(Colaborador obj) {
        return repository.save(obj);
    }
    
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    
    public Colaborador update(Long id, Colaborador obj) {
        try {
            Colaborador entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Colaborador entity, Colaborador obj) {
        entity.setNome(obj.getNome());
        entity.setFuncao(obj.getFuncao());
        entity.setMatricula(obj.getMatricula());
        entity.setTelefone(obj.getTelefone());
        entity.setEmail(obj.getEmail());
        entity.setAtivo(obj.getAtivo());
    }
}
