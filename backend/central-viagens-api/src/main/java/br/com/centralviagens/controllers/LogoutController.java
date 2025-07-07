package br.com.centralviagens.controllers;

import br.com.centralviagens.models.BlackListedToken;
import br.com.centralviagens.repositories.BlackListedTokenRepository;
import br.com.centralviagens.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/custom-logout")
public class LogoutController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BlackListedTokenRepository blacklistRepo;

    @PostMapping
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            LocalDateTime expiration = jwtService.extractExpiration(token)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            blacklistRepo.save(new BlackListedToken(token, expiration));
        }

        return ResponseEntity.ok("Logout efetuado com sucesso.");
    }

}
