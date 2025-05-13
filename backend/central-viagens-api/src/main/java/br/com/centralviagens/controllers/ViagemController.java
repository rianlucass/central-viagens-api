package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.request.ViagemRequestDTO;
import br.com.centralviagens.dtos.response.ViagemResponseDTO;
import br.com.centralviagens.services.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motorista/cadastro-viagem")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @PostMapping("/viagem")
    public ResponseEntity<ViagemResponseDTO> registerTrip(@PathVariable ViagemRequestDTO requestDTO) {
        ViagemResponseDTO responseDTO = viagemService.registerTrip(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
