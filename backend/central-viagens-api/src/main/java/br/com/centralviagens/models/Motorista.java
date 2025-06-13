package br.com.centralviagens.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "motoristas")
@Getter
@Setter
public class Motorista {

    @Id
    private String id;
    private String cnh_categoria;
    private String cnh_numero;
    private LocalDate validade_cnh;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "motorista")
    private List<Viagem> viagensCriadas;

    @OneToMany(mappedBy = "motorista")
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes;
}
