package br.com.centralviagens.dtos.response;

import br.com.centralviagens.models.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private UserType userType;
    private String userId;

}
