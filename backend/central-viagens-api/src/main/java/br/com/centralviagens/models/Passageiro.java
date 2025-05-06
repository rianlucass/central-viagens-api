package br.com.centralviagens.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "passageiros")
@Getter
@Setter
public class Passageiro {
    @Id
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "passageiros_viagens",
            joinColumns = @JoinColumn(name = "passageiro_id"),
            inverseJoinColumns = @JoinColumn(name = "viagem_id")
    )
    private List<Viagem> viagensCompradas;
}
