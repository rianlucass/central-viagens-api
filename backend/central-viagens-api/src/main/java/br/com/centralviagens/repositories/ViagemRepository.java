package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, String> {

    List<Viagem> findByMotoristaUsuarioId(String usuarioId);

}
