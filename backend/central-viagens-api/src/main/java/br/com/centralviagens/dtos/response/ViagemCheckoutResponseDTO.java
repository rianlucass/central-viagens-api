package br.com.centralviagens.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemCheckoutResponseDTO (
        String id,
        String estadoOrigem,
        String cidadeOrigem,
        String estadoDestino,
        String cidadeDestino,
        LocalDateTime dataPartida,
        BigDecimal preco,
        String veiculo,
        int capacidade,
        String nomeMotorista,
        String pontoEmbarque,
        String pontoDesembarque
){ }
