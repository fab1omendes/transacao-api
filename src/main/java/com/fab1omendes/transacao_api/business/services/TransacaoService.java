package com.fab1omendes.transacao_api.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fab1omendes.transacao_api.controller.dtos.TransacaoRequestDTO;   
import com.fab1omendes.transacao_api.infraestructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void addTransacao(TransacaoRequestDTO dto) {

        log.info("Adicionando transação: {}", dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maior que a data e hora atual");
            throw new UnprocessableEntity("Data e hora maior que a data e hora atual");
        }
        
        if (dto.valor() < 0) {
            log.error("Valor menor que zero");
            throw new UnprocessableEntity("Valor menor que zero");
        }

        listaTransacoes.add(dto);
        log.info("Transação adicionada com sucesso");       
    }

    public void limparTransacoes() {
        log.info("Iniciado limpeza de transações");
        listaTransacoes.clear();
        log.info("Transações limpas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Buscando transações por tempo {} segundos", intervaloBusca);
        OffsetDateTime dataIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataIntervalo)).toList();
    }
    
}