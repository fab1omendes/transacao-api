package com.fab1omendes.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fab1omendes.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.fab1omendes.transacao_api.controller.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    public final TransacaoService transacaoService; 

    public EstatisticaResponseDTO calcularEstatisticas(Integer intervaloBusca) {

       List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);
       log.info("Busca de estatísticas de transações concluídas no período de {} segundos", intervaloBusca);

       if (transacoes.isEmpty()) {
           return new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
       }

       DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics(); 

       log.info("Estatísticas calculadas com sucesso");
        return new EstatisticaResponseDTO(
            estatisticasTransacoes.getCount(),
            estatisticasTransacoes.getSum(),
            estatisticasTransacoes.getAverage(),
            estatisticasTransacoes.getMin(),
            estatisticasTransacoes.getMax()
        );
    }
     
}
