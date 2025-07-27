package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.response.ViagemCheckoutResponseDTO;
import br.com.centralviagens.dtos.response.ViagemResponseDTO;
import br.com.centralviagens.services.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/viagens")
public class ViagemPublicController {

    @Autowired
    private ViagemService viagemService;

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ViagemResponseDTO>> buscarViagensDisponiveis(
            @RequestParam(required = false) String estadoOrigem,
            @RequestParam(required = false) String cidadeOrigem,
            @RequestParam(required = false) String estadoDestino,
            @RequestParam(required = false) String cidadeDestino,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPartida) {

        List<ViagemResponseDTO> viagens = viagemService.searchDisponiveis(
                estadoOrigem,
                cidadeOrigem,
                estadoDestino,
                cidadeDestino,
                dataPartida
        );
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<ViagemResponseDTO>> getAllTripsAvailable(@PageableDefault(size = 6, sort = "dataPartida", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ViagemResponseDTO> viagens = viagemService.getAllTripsAvailable(pageable);
        return ResponseEntity.ok(viagens);
    }

    @GetMapping("/{id}/checkout")
    public ResponseEntity<ViagemCheckoutResponseDTO> checkout(@PathVariable String id) {
        ViagemCheckoutResponseDTO responseDTO = viagemService.checkoutDetails(id);
        return ResponseEntity.ok(responseDTO);
    }

}
