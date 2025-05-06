package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<Passageiro, String> {
}
