package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.ViagemRequestDTO;
import br.com.centralviagens.dtos.response.ViagemResponseDTO;
import br.com.centralviagens.models.Motorista;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.models.Veiculo;
import br.com.centralviagens.models.Viagem;
import br.com.centralviagens.models.enums.ViagemStatus;
import br.com.centralviagens.repositories.MotoristaRepository;
import br.com.centralviagens.repositories.UsuarioRepository;
import br.com.centralviagens.repositories.VeiculoRepository;
import br.com.centralviagens.repositories.ViagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Transactional
    public ViagemResponseDTO registerTrip(ViagemRequestDTO requestDTO) {

        Veiculo veiculo = veiculoRepository.findById(requestDTO.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado!"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        Motorista motorista = motoristaRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Motorista não associado"));

        Viagem viagem = new Viagem();
        viagem.setOrigem(requestDTO.getOrigem());
        viagem.setDestino(requestDTO.getDestino());
        viagem.setDataPartida(requestDTO.getDataPartida());
        viagem.setValor(requestDTO.getValor());
        viagem.setBagagemGrande(requestDTO.isBagagemGrande());
        viagem.setAnimaisEstimação(requestDTO.isAnimaisEstimacao());
        viagem.setObservacoes(requestDTO.getObservacoes());
        viagem.setVeiculo(veiculo);
        viagem.setMotorista(motorista);
        viagem.setViagemStatus(ViagemStatus.DISPONIVEL);

        viagem = viagemRepository.save(viagem);

        ViagemResponseDTO responseDTO = new ViagemResponseDTO(
                viagem.getId(),
                viagem.getOrigem(),
                viagem.getDestino(),
                viagem.getDataPartida(),
                viagem.getValor(),
                viagem.getVeiculo().getMarca() + " - " + viagem.getVeiculo().getModelo(),
                viagem.getViagemStatus()
        );
        return responseDTO;
    }

    public List<ViagemResponseDTO> getAllTrip() {
        return viagemRepository.findAll().stream().map(viagem -> {
            ViagemResponseDTO responseDTO = new ViagemResponseDTO(
                    viagem.getId(),
                    viagem.getOrigem(),
                    viagem.getDestino(),
                    viagem.getDataPartida(),
                    viagem.getValor(),
                    viagem.getVeiculo().getMarca() +" - "+ viagem.getVeiculo().getModelo(),
                    viagem.getViagemStatus()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }

}
