package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, String> {
}
