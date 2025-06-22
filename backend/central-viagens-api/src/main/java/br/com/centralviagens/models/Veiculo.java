package br.com.centralviagens.models;

import br.com.centralviagens.models.enums.TransportType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String modelo;

    @Enumerated(EnumType.STRING)
    private TransportType tipo;

    @Column(unique = true)
    private String marca;

    @Column(unique = true)
    private String placa;

    private int ano;
    private int capacidade;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
}
