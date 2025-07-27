package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Viagem;
import br.com.centralviagens.models.enums.ViagemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ViagemRepository extends JpaRepository<Viagem, String> {

    List<Viagem> findByMotoristaUsuarioId(String usuarioId);

    Page<Viagem> findByViagemStatus(ViagemStatus status, Pageable pageable);

    @Query("""
    SELECT v FROM Viagem v WHERE 
    v.viagemStatus = :status AND
    (:estadoOrigem IS NULL OR v.estadoOrigem ILIKE %:estadoOrigem%) AND
    (:cidadeOrigem IS NULL OR v.cidadeOrigem ILIKE %:cidadeOrigem%) AND
    (:estadoDestino IS NULL OR v.estadoDestino ILIKE %:estadoDestino%) AND
    (:cidadeDestino IS NULL OR v.cidadeDestino ILIKE %:cidadeDestino%) AND
    (:dataPartida IS NULL OR CAST(v.dataPartida AS date) = :dataPartida)
    """)
    List<Viagem> findByViagemStatusAndFilters(
            @Param("status") ViagemStatus status,
            @Param("estadoOrigem") String estadoOrigem,
            @Param("cidadeOrigem") String cidadeOrigem,
            @Param("estadoDestino") String estadoDestino,
            @Param("cidadeDestino") String cidadeDestino,
            @Param("dataPartida") LocalDate dataPartida
    );
}
