package br.com.centralviagens.dtos.request;

import br.com.centralviagens.models.Veiculo;
import br.com.centralviagens.models.Viagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MotoristaRequestDTO{
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String cnh_categoria;

        @NotBlank(message = "Este campo não pode ser nulo.")
        private String cnhNumero;

        @NotNull(message = "Este campo não pode ser nulo.")
        private LocalDate validade_cnh;

        private String usuario;
        private List<Viagem> viagensCriadas;
        private List<Veiculo> veiculos;
}
