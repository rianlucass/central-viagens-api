package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.Viagem;

import java.util.List;

public record PassageiroResponseDTO(
        String id,
        String username,
        String cep,
        String cpf,
        List<Viagem> viagensCompradas
) { }
