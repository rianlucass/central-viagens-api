package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.TransportType;

public record VeiculoResponseDTO(
        String id,
        String modelo,
        TransportType tipo,
        String marca,
        String placa,
        int ano,
        String cor,
        int capacidade
) { }
