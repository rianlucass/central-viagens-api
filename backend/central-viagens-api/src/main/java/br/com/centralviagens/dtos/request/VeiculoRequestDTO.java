package br.com.centralviagens.dtos.request;

import br.com.centralviagens.models.Motorista;
import br.com.centralviagens.models.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VeiculoRequestDTO {
    private String modelo;
    private TransportType tipo;
    private String marca;
    private String placa;
    private int ano;
    private int capacidade;
}
