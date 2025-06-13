package br.com.centralviagens.repositories;

import br.com.centralviagens.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    @Query("SELECT COALESCE(SUM(t.data), 0) FROM Transacao t " +
            "WHERE t.motorista.id = :motoristaId " +
            "AND t.data BETWEEN :dataInicio AND :dataFim")
    BigDecimal obterSaldoPorMes(@Param("motoristaId") String motoristaId,
                                @Param("dataInicio") LocalDate dataInicio,
                                @Param("dataFim") LocalDate dataFim);


    List<Transacao> findByMotoristaIdAndDataBetween(String motoristaId, LocalDate dataInicio, LocalDate dataFim);

}
