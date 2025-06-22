package br.com.centralviagens.repositories;

import br.com.centralviagens.models.DadosPessoais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais,String> {

    boolean existsByCpf(String cpf);
    boolean existsByTelefone(String telefone);

}
