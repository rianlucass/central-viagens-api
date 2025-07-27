package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.ViagemStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ViagemDetalhesResponseDTO (
        String id,
        String estadoOrigem,
        String cidadeOrigem,
        String estadoDestino,
        String cidadeDestino,
        LocalDateTime dataPartida,
        BigDecimal valor,
        String detalhesVeiculo,
        int capacidadeDisponivel,
        ViagemStatus viagemStatus,
        List<ListaPassageiroResponseDTO> passageiro
){ }
