package br.com.centralviagens.controllers;


import br.com.centralviagens.dtos.register.RegisterMotoristaUsuarioDTO;
import br.com.centralviagens.dtos.response.MotoristaResponseDTO;
import br.com.centralviagens.models.enums.UserStatus;
import br.com.centralviagens.models.enums.UserType;
import br.com.centralviagens.services.MotoristaService;
import br.com.centralviagens.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping
    public ResponseEntity<MotoristaResponseDTO> registerMotorista(@Valid @RequestBody RegisterMotoristaUsuarioDTO requestDTO) {
        requestDTO.getUsuario().setType(UserType.MOTORISTA);
        requestDTO.getUsuario().setStatus(UserStatus.ATIVO);
        MotoristaResponseDTO responseDTO = motoristaService.registerDriver(requestDTO.getMotorista(), requestDTO.getUsuario(), requestDTO.getDadosPessoais(),  requestDTO.getEndereco());
        return ResponseEntity.ok(responseDTO);
    }

}
