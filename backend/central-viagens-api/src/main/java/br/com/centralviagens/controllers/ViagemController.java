package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.request.ViagemRequestDTO;
import br.com.centralviagens.dtos.response.ViagemResponseDTO;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.services.ViagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorista/viagem")
@CrossOrigin(origins = "http://localhost:3000")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @PostMapping("/cadastro")
    public ResponseEntity<ViagemResponseDTO> registerTrip(@Valid @RequestBody ViagemRequestDTO requestDTO) {
        ViagemResponseDTO responseDTO = viagemService.registerTrip(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ViagemResponseDTO>> listTripByUser(@AuthenticationPrincipal Usuario usuarioAutenticado) {
        List<ViagemResponseDTO> viagens = viagemService.getAllTripByUser(usuarioAutenticado);
        return ResponseEntity.ok(viagens);
    }

}
