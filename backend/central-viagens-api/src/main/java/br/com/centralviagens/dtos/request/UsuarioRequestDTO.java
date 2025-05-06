package br.com.centralviagens.dtos.request;

import br.com.centralviagens.models.DadosPessoais;
import br.com.centralviagens.models.Endereco;
import br.com.centralviagens.models.Motorista;
import br.com.centralviagens.models.Passageiro;
import br.com.centralviagens.models.enums.UserStatus;
import br.com.centralviagens.models.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioRequestDTO {
    @NotBlank(message = "Este campo não pode ser nulo.")
    @Size(min = 4, max = 12, message = "Quantidade de caractere nao permitido")
    private String username;

    @NotBlank(message = "Este campo não pode ser nulo.")
    @Email(message = "E-mail invalid")
    private String email;

    @NotBlank(message = "Este campo não pode ser nulo.")
    @Size(min = 6, max = 255, message = "Quantidade de caractere nao permitido")
    private String password;

    //@NotNull(message = "Este campo não pode ser nulo.")
    private UserType type;

    @NotNull(message = "Este campo não pode ser nulo.")
    private UserStatus status;

    private DadosPessoais dadosPessoais;
    private Endereco endereco;
    private Motorista motorista;
    private Passageiro passageiro;
}
