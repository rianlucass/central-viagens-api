package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.request.VeiculoRequestDTO;
import br.com.centralviagens.dtos.response.VeiculoResponseDTO;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.services.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorista")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/veiculo/cadastro")
    public ResponseEntity<VeiculoResponseDTO> registerCar(@Valid @RequestBody VeiculoRequestDTO requestDTO) {
        VeiculoResponseDTO responseDTO = veiculoService.registerCar(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/veiculos")
    public ResponseEntity<List<VeiculoResponseDTO>> listarVeiculosDoUsuarioAutenticado(@AuthenticationPrincipal Usuario usuarioAutenticado) {
        List<VeiculoResponseDTO> veiculos = veiculoService.getAllCarsByUsers(usuarioAutenticado);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/todos-veiculos")
    public List<VeiculoResponseDTO> getAllCars() {
        return veiculoService.getAllCars();
    }
}
