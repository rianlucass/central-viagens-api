package br.com.centralviagens.dtos.response;

public record EnderecoResponseDTO(
        String id,
        String estado,
        String cidade,
        String cep,
        String rua,
        String bairro,
        String complemento,
        String numero
) { }
