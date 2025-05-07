package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.Veiculo;
import br.com.centralviagens.models.Viagem;

import java.time.LocalDate;
import java.util.List;

public record MotoristaResponseDTO(
        String id,
        String cnh_categoria,
        String cnh_numero,
        LocalDate validade_cnh,
        String username,
        String cpf,
        String cep,
        List<Viagem> viagensCriadas,
        List<Veiculo> veiculos
) { }
