package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Motorista;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

    boolean existsByPlaca(String placa);
    List<Veiculo> findByMotoristaUsuarioId(String usuarioId);

}
