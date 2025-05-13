package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.request.VeiculoRequestDTO;
import br.com.centralviagens.dtos.response.VeiculoResponseDTO;
import br.com.centralviagens.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motorista/cadastro-veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/veiculo")
    public ResponseEntity<VeiculoResponseDTO> registerCar(@PathVariable VeiculoRequestDTO requestDTO) {
        VeiculoResponseDTO responseDTO = veiculoService.registerCar(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
