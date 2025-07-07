package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.ViagemStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemResponseDTO(
        String id,
        String estadoOrigem,
        String cidadeOrigem,
        String estadoDestino,
        String cidadeDestino,
        LocalDateTime dataPartida,
        BigDecimal valor,
        String detalhesVeiculo,
        int capacidadeDisponivel,
        ViagemStatus viagemStatus
) {
}
