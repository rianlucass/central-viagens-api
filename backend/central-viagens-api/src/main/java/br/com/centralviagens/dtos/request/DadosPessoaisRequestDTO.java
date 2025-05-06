package br.com.centralviagens.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DadosPessoaisRequestDTO {
        @NotBlank(message = "Este campo não pode ser nulo.")
        @Size(min = 20, max = 255, message = "Quantidade de caractere nao permitido")
        private String nome;

        @NotBlank(message = "Este campo não pode ser nulo.")
        private LocalDate dataNascimento;

        @NotBlank(message = "Este campo não pode ser nulo.")
        @Size(max = 14, message = "Quantidade de caractere nao permitido")
        private String cpf;

        @NotBlank(message = "Este campo não pode ser nulo.")
        @Size(max = 14, message = "Quantidade de caractere nao permitido")
        private String telefone;
}
