package br.com.centralviagens.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "dados_pessoais")
@Getter
@Setter
public class DadosPessoais {
    @Id
    private String id;
    private String nome;
    private LocalDate dataNascimento;

    @Column(unique = true)
    private String cpf;
    private String telefone;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
