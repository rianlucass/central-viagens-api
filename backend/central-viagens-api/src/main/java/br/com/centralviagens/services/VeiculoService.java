package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.VeiculoRequestDTO;
import br.com.centralviagens.dtos.response.VeiculoResponseDTO;
import br.com.centralviagens.models.Motorista;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.models.Veiculo;
import br.com.centralviagens.repositories.MotoristaRepository;
import br.com.centralviagens.repositories.UsuarioRepository;
import br.com.centralviagens.repositories.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Transactional
    public VeiculoResponseDTO registerCar(VeiculoRequestDTO requestDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        Motorista motorista = motoristaRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Motorista não associado"));

        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(requestDTO.getModelo());
        veiculo.setTipo(requestDTO.getTipo());
        veiculo.setMarca(requestDTO.getMarca());
        veiculo.setPlaca(requestDTO.getPlaca());
        veiculo.setAno(requestDTO.getAno());
        veiculo.setCapacidade(requestDTO.getCapacidade());
        veiculo.setMotorista(motorista);
        veiculo = veiculoRepository.save(veiculo);

        VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getModelo(),
                veiculo.getTipo(),
                veiculo.getMarca(),
                veiculo.getPlaca(),
                veiculo.getAno(),
                veiculo.getCapacidade(),
                veiculo.getMotorista().getUsuario().getUsername()
        );

        return responseDTO;
    }

    public List<VeiculoResponseDTO> getAllCars() {
        return veiculoRepository.findAll().stream().map(veiculo -> {
            VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(
                    veiculo.getId(),
                    veiculo.getModelo(),
                    veiculo.getTipo(),
                    veiculo.getMarca(),
                    veiculo.getPlaca(),
                    veiculo.getAno(),
                    veiculo.getCapacidade(),
                    veiculo.getMotorista().getUsuario().getUsername()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }
}
