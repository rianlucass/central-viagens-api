package br.com.centralviagens.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemResponseDTO(
        String id,
        String origem,
        String destino,
        LocalDateTime dataPartida,
        BigDecimal preco,
        String detalhesVeiculo
) {
}
