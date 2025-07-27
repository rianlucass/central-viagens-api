package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.DadosPessoais;
import br.com.centralviagens.models.Endereco;
import br.com.centralviagens.models.Viagem;

import java.util.List;

public record PassageiroResponseDTO(
        String id,
        String username,
        String cpf,
        String cep,
        List<Viagem> viagens
) { }
