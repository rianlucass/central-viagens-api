package br.com.centralviagens.dtos.request;

import br.com.centralviagens.models.Viagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PassageiroRequestDTO {
    private List<Viagem> viagensCompradas;
}
