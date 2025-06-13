package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.ViagemStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemResponseDTO(
        String id,
        String origem,
        String destino,
        LocalDateTime dataPartida,
        BigDecimal valor,
        String detalhesVeiculo,
        ViagemStatus viagemStatus
) {
}
