package br.com.centralviagens.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
public class BlackListedToken {

    @Id
    private String token;
    private LocalDateTime expirateDate;

    public BlackListedToken() {}

}
