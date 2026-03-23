package com.fab1omendes.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fab1omendes.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.fab1omendes.transacao_api.controller.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    public final TransacaoService transacaoService; 

    public EstatisticaResponseDTO calcularEstatisticas(Integer intervaloBusca) {

       List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

       DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics(); 

        return new EstatisticaResponseDTO(
            estatisticasTransacoes.getCount(),
            estatisticasTransacoes.getSum(),
            estatisticasTransacoes.getAverage(),
            estatisticasTransacoes.getMin(),
            estatisticasTransacoes.getMax()
        );
    }
     
}
