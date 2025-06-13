package br.com.centralviagens.dtos.response;

import java.math.BigDecimal;

public record TransacaoResponseDTO(
        String motorista_id,
        int mes,
        int ano,
        BigDecimal saldo
){ }
