package br.com.centralviagens.models;

import br.com.centralviagens.models.enums.ViagemStatus;
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
    private String estadoOrigem;
    private String cidadeOrigem;
    private String estadoDestino;
    private String cidadeDestino;
    private LocalDateTime dataPartida;
    private BigDecimal valor;
    private boolean bagagemGrande;
    private boolean animaisEstimacao;
    private String observacoes;
    private int capacidadeDisponivel;
    private ViagemStatus viagemStatus;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @ManyToMany(mappedBy = "viagensCompradas")
    private List<Passageiro> passageiros;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
}
