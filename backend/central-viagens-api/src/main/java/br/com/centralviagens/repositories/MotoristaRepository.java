package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<Motorista, String> {
}
