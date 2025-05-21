package br.com.centralviagens.controllers.passageiro;

import br.com.centralviagens.dtos.register.RegisterPassageiroUsuarioDTO;
import br.com.centralviagens.dtos.response.PassageiroResponseDTO;
import br.com.centralviagens.models.enums.UserStatus;
import br.com.centralviagens.models.enums.UserType;
import br.com.centralviagens.services.PassageiroService;
import br.com.centralviagens.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro-passageiro")
@CrossOrigin(origins = "http://localhost:3000")
public class PassageiroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PassageiroService passageiroService;

    @PostMapping("/passageiro")
    public ResponseEntity<PassageiroResponseDTO> registerPassageiro(@Valid @RequestBody RegisterPassageiroUsuarioDTO requestDTO) {
        requestDTO.getUsuario().setType(UserType.PASSAGEIRO);
        requestDTO.getUsuario().setStatus(UserStatus.ATIVO);
        PassageiroResponseDTO responseDTO = passageiroService.registerPassenger(requestDTO.getPassageiro(), requestDTO.getUsuario(), requestDTO.getDadosPessoais(), requestDTO.getEndereco());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public List<PassageiroResponseDTO> getAllPassengers() {
        return passageiroService.getAllPassenger();
    }

}
