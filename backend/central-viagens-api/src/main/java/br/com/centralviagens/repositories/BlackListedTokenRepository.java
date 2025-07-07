package br.com.centralviagens.repositories;

import br.com.centralviagens.models.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, String> {
    boolean existsByToken(String token);
}
