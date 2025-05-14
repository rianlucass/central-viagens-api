package br.com.centralviagens.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ViagemRequestDTO {
        @NotBlank(message = "Este campo não pode ser nulo.")
        private String origem;

        @NotBlank(message = "Este campo não pode ser nulo.")
        private String destino;

        @NotNull(message = "Este campo não pode ser nulo.")
        private LocalDateTime dataPartida;

        @NotNull(message = "Este campo não pode ser nulo.")
        private BigDecimal preco;

        @NotNull(message = "Este campo não pode ser nulo.")
        private boolean bagagemGrande;

        @NotNull(message = "Este campo não pode ser nulo.")
        private boolean animaisEstimacao;

        @NotNull(message = "Este campo não pode ser nulo.")
        private String observacoes;

        @NotNull(message = "Este campo não pode ser nulo.")
        private String veiculoId;
}
