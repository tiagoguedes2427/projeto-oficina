package com.tiagogguedes.oficina.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogguedes.oficina.dto.ColaboradorDTO;
import com.tiagogguedes.oficina.dto.ServicoDTO;
import com.tiagogguedes.oficina.dto.VagaDetalhadaDTO;
import com.tiagogguedes.oficina.entities.Servico;
import com.tiagogguedes.oficina.entities.Vaga;
import com.tiagogguedes.oficina.services.ServicoService;
import com.tiagogguedes.oficina.services.VagaService;

@RestController
@RequestMapping(value = "/vagas-detalhadas")
public class VagaDetalhadaController {
    
    @Autowired
    private VagaService vagaService;
    
    @Autowired
    private ServicoService servicoService;
    
    @GetMapping
    public ResponseEntity<List<VagaDetalhadaDTO>> findAllDetalhadas() {
        // Buscar todas as vagas
        List<Vaga> vagas = vagaService.findAll();
        
        // Buscar todos os serviços
        List<Servico> servicos = servicoService.findAll();
        
        // Criar DTOs detalhados
        List<VagaDetalhadaDTO> vagasDetalhadas = new ArrayList<>();
        
        for (Vaga vaga : vagas) {
            VagaDetalhadaDTO vagaDTO = new VagaDetalhadaDTO(vaga);
            
            // Filtrar serviços desta vaga
            List<Servico> servicosDaVaga = servicos.stream()
                .filter(s -> s.getVaga() != null && s.getVaga().getId().equals(vaga.getId()))
                .collect(Collectors.toList());
            
            // Adicionar serviços ao DTO da vaga
            for (Servico servico : servicosDaVaga) {
                vagaDTO.addServico(new ServicoDTO(servico));
            }
            
            // Determinar status da vaga com base nos serviços
            if (!servicosDaVaga.isEmpty()) {
                vagaDTO.setStatus("Ocupado");
            } else {
                vagaDTO.setStatus("Livre");
            }
            
            vagasDetalhadas.add(vagaDTO);
        }
        
        return ResponseEntity.ok().body(vagasDetalhadas);
    }
}
