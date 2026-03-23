package com.fab1omendes.transacao_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fab1omendes.transacao_api.business.services.EstatisticaService;
import com.fab1omendes.transacao_api.controller.dtos.EstatisticaResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;
    
    public ResponseEntity<EstatisticaResponseDTO> buscarEstatisticas(
        @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticaService.calcularEstatisticas(intervaloBusca));
    }
    
}
