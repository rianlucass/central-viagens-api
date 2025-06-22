package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

    boolean existsByPlaca(String placa);

}
