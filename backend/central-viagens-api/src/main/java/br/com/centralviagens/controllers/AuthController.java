package br.com.centralviagens.controllers;


import br.com.centralviagens.dtos.request.LoginResquestDTO;
import br.com.centralviagens.dtos.response.LoginResponseDTO;
import br.com.centralviagens.exceptions.CredenciaisInvalidasException;
import br.com.centralviagens.models.BlackListedToken;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.repositories.BlackListedTokenRepository;
import br.com.centralviagens.security.CustomUserDetails;
import br.com.centralviagens.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginResquestDTO request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            Usuario usuario = userDetails.getUsuario();

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                    jwtToken,
                    usuario.getType(),
                    usuario.getId(),
                    usuario.getUsername()
            );

            return ResponseEntity.ok(loginResponseDTO);

        } catch (BadCredentialsException exception) {
            throw new CredenciaisInvalidasException("Usuário ou senha inválida");
        }
    }
}