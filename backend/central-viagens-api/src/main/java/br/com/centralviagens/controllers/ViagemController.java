package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.request.ViagemRequestDTO;
import br.com.centralviagens.dtos.request.ViagemUpdateRequestDTO;
import br.com.centralviagens.dtos.response.ViagemDetalhesResponseDTO;
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

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<ViagemDetalhesResponseDTO> detailsTripByUser (@PathVariable String id) {
        ViagemDetalhesResponseDTO viagemDetalhesResponseDTO = viagemService.detailsTripByUser(id);
        return ResponseEntity.ok(viagemDetalhesResponseDTO);
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<ViagemResponseDTO> iniciarViagem(@PathVariable String id, @AuthenticationPrincipal Usuario usuarioAutenticado) {
        ViagemResponseDTO responseDTO = viagemService.iniciarViagem(id, usuarioAutenticado);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> updateTrip(
            @PathVariable String id,
            @Valid @RequestBody ViagemUpdateRequestDTO updateDTO,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        ViagemResponseDTO responseDTO = viagemService.updateTrip(id, updateDTO, usuarioAutenticado);
        return ResponseEntity.ok(responseDTO);
    }



}
