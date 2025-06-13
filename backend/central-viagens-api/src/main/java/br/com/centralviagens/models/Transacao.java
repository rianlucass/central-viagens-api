package br.com.centralviagens.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal saldo;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;


}
