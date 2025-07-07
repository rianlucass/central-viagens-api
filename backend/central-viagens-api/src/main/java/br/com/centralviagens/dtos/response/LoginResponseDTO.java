package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record LoginResponseDTO(
        String token,
        UserType userType,
        String userId,
        String username
) { }
