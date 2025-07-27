package br.com.centralviagens.dtos.request;

import br.com.centralviagens.models.enums.ViagemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ViagemUpdateRequestDTO {
    private LocalDateTime dataPartida;
    private BigDecimal valor;
    private ViagemStatus viagemStatus;
}
