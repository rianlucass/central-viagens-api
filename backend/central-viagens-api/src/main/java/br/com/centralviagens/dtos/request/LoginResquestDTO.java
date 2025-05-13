package br.com.centralviagens.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResquestDTO {
    @NotBlank(message = "Este campo não pode ser nulo.")
    @Size(min = 4, max = 12, message = "Quantidade de caractere nao permitido")
    private String username;

    @NotBlank(message = "Este campo não pode ser nulo.")
    @Size(min = 6, max = 255, message = "Quantidade de caractere nao permitido")
    private String password;
}
