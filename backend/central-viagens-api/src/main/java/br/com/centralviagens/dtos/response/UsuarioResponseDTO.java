package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.UserStatus;
import br.com.centralviagens.models.enums.UserType;

public record UsuarioResponseDTO(
        String id,
        String username,
        String email,
        String password,
        UserType type,
        UserStatus status,
        String telefone
) { }
