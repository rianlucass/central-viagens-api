package br.com.centralviagens.controllers;


import br.com.centralviagens.dtos.request.LoginResquestDTO;
import br.com.centralviagens.dtos.response.LoginResponseDTO;
import br.com.centralviagens.security.CustomUserDetails;
import br.com.centralviagens.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginResquestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(
                jwtToken,
                userDetails.getUsuario().getType(),
                userDetails.getUsuario().getId()
        ));
    }
}