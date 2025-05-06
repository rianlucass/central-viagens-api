package br.com.centralviagens.dtos.response;

import java.time.LocalDate;

public record DadosPessoaisResponseDTO(
        String id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String telefone
) { }
