package br.com.centralviagens.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "viagens")
@Getter
@Setter
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String origem;
    private String destino;
    private LocalDateTime dataPartida;
    private BigDecimal preco;
    private boolean bagagemGrande;
    private boolean animaisEstimação;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @ManyToMany(mappedBy = "viagensCompradas")
    private List<Passageiro> passageiros;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
}
