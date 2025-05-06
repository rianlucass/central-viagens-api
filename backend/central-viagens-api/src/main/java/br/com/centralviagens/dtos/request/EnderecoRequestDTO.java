package br.com.centralviagens.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnderecoRequestDTO {
        @Size(max = 100, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String estado;

        @Size(max = 100, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String cidade;

        @Size(min = 8, max = 8, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String cep;

        @Size(max = 100, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String rua;

        @Size(max = 100, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String bairro;

        @Size(max = 255, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String complemento;

        @Size(max = 10, message = "Quantidade de caractere nao permitido")
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String numero;
}
