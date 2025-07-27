package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.ViagemRequestDTO;
import br.com.centralviagens.dtos.request.ViagemUpdateRequestDTO;
import br.com.centralviagens.dtos.response.*;
import br.com.centralviagens.models.*;
import br.com.centralviagens.models.enums.ViagemStatus;
import br.com.centralviagens.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    private PassageiroRepository passageiroRepository;

    private ViagemResponseDTO toDTO(Viagem viagem) {
        return new ViagemResponseDTO(
                viagem.getId(),
                viagem.getEstadoOrigem(),
                viagem.getCidadeOrigem(),
                viagem.getEstadoDestino(),
                viagem.getCidadeDestino(),
                viagem.getDataPartida(),
                viagem.getValor(),
                viagem.getVeiculo().getModelo() + " - " + viagem.getVeiculo().getPlaca(),
                viagem.getCapacidadeDisponivel(),
                viagem.getViagemStatus()
        );
    }

    @Transactional
    public ViagemResponseDTO registerTrip(ViagemRequestDTO requestDTO) {

        Veiculo veiculo = veiculoRepository.findById(requestDTO.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado!"));

        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = usuarioRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        Motorista motorista = motoristaRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Motorista não associado"));

        Viagem viagem = new Viagem();
        viagem.setEstadoOrigem(requestDTO.getEstadoOrigem());
        viagem.setCidadeOrigem(requestDTO.getCidadeOrigem());
        viagem.setEstadoDestino(requestDTO.getEstadoDestino());
        viagem.setCidadeDestino(requestDTO.getCidadeDestino());
        viagem.setDataPartida(requestDTO.getDataPartida());
        viagem.setValor(requestDTO.getValor());
        viagem.setBagagemGrande(requestDTO.isBagagemGrande());
        viagem.setAnimaisEstimacao(requestDTO.isAnimaisEstimacao());
        viagem.setObservacoes(requestDTO.getObservacoes());
        viagem.setVeiculo(veiculo);
        viagem.setMotorista(motorista);
        viagem.setCapacidadeDisponivel(requestDTO.getCapacidadeDisponivel());
        viagem.setViagemStatus(ViagemStatus.DISPONIVEL);

        viagem = viagemRepository.save(viagem);

        ViagemResponseDTO responseDTO = new ViagemResponseDTO(
                viagem.getId(),
                viagem.getEstadoOrigem(),
                viagem.getCidadeOrigem(),
                viagem.getEstadoDestino(),
                viagem.getCidadeDestino(),
                viagem.getDataPartida(),
                viagem.getValor(),
                viagem.getVeiculo().getMarca() + " - " + viagem.getVeiculo().getModelo(),
                viagem.getCapacidadeDisponivel(),
                viagem.getViagemStatus()
        );
        return responseDTO;
    }

    public List<ViagemResponseDTO> getAllTrip() {
        return viagemRepository.findAll().stream().map(viagem -> {
            ViagemResponseDTO responseDTO = new ViagemResponseDTO(
                    viagem.getId(),
                    viagem.getEstadoOrigem(),
                    viagem.getCidadeOrigem(),
                    viagem.getEstadoDestino(),
                    viagem.getCidadeDestino(),
                    viagem.getDataPartida(),
                    viagem.getValor(),
                    viagem.getVeiculo().getMarca() +" - "+ viagem.getVeiculo().getModelo(),
                    viagem.getCapacidadeDisponivel(),
                    viagem.getViagemStatus()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }

    public List<ViagemResponseDTO> getAllTripByUser(Usuario usuario ) {
        return viagemRepository.findByMotoristaUsuarioId(usuario.getMotorista().getId()).stream().map(viagem -> {
            ViagemResponseDTO responseDTO = new ViagemResponseDTO(
            viagem.getId(),
            viagem.getEstadoOrigem(),
            viagem.getCidadeOrigem(),
            viagem.getEstadoDestino(),
            viagem.getCidadeDestino(),
            viagem.getDataPartida(),
            viagem.getValor(),
            viagem.getVeiculo().getModelo() + " - " + viagem.getVeiculo().getPlaca(),
            viagem.getCapacidadeDisponivel(),
            viagem.getViagemStatus()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }

    public List<ViagemResponseDTO> searchDisponiveis(String estadoOrigem, String cidadeOrigem, String estadoDestino, String cidadeDestino, LocalDate dataPartida) {

        List<Viagem> viagens = viagemRepository.findByViagemStatusAndFilters(
                ViagemStatus.DISPONIVEL,
                estadoOrigem,
                cidadeOrigem,
                estadoDestino,
                cidadeDestino,
                dataPartida
        );

        if (dataPartida != null) {
            LocalDateTime inicio = dataPartida.atStartOfDay();
            LocalDateTime fim = dataPartida.atTime(LocalTime.MAX);

            viagens = viagens.stream()
                    .filter(v -> !v.getDataPartida().isBefore(inicio) && !v.getDataPartida().isAfter(fim))
                    .collect(Collectors.toList());
        }

        return viagens.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Page<ViagemResponseDTO> getAllTripsAvailable(Pageable pageable) {
        return viagemRepository.findByViagemStatus(ViagemStatus.DISPONIVEL, pageable)
                .map(viagem -> new ViagemResponseDTO(
                        viagem.getId(),
                        viagem.getEstadoOrigem(),
                        viagem.getCidadeOrigem(),
                        viagem.getEstadoDestino(),
                        viagem.getCidadeDestino(),
                        viagem.getDataPartida(),
                        viagem.getValor(),
                        viagem.getVeiculo().getMarca() + " - " + viagem.getVeiculo().getModelo(),
                        viagem.getCapacidadeDisponivel(),
                        viagem.getViagemStatus()
                ));
    }

    public ViagemDetalhesResponseDTO detailsTripByUser(String viagemId) {
        Usuario user  = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        Motorista motorista = motoristaRepository.findById(usuario.getId()).orElseThrow(() -> new RuntimeException("Motorista não associado"));
        Viagem viagem = viagemRepository.findById(viagemId).orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        if (!viagem.getMotorista().getId().equals(motorista.getId())) {
            throw new RuntimeException("Você não tem permissão para ver essa viagem.");
        }

        List<ListaPassageiroResponseDTO> passageiros = viagem.getPassageiros().stream()
                .map(p -> new ListaPassageiroResponseDTO(p.getUsuario().getUsername()))
                .collect(Collectors.toList());

        return new ViagemDetalhesResponseDTO(
                viagem.getId(),
                viagem.getEstadoOrigem(),
                viagem.getCidadeOrigem(),
                viagem.getEstadoDestino(),
                viagem.getCidadeDestino(),
                viagem.getDataPartida(),
                viagem.getValor(),
                viagem.getVeiculo().getModelo() + " - " + viagem.getVeiculo().getPlaca(),
                viagem.getCapacidadeDisponivel(),
                viagem.getViagemStatus(),
                passageiros
        );
    }

    @Transactional
    public ViagemResponseDTO iniciarViagem(String viagemId, Usuario usuarioAutenticado) {
        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        Motorista motorista = motoristaRepository.findById(usuarioAutenticado.getId())
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        if (!viagem.getMotorista().getId().equals(motorista.getId())) {
            throw new RuntimeException("Você não tem permissão para iniciar esta viagem.");
        }

        viagem.setViagemStatus(ViagemStatus.EM_ANDAMENTO);
        viagemRepository.save(viagem);

        return toDTO(viagem);
    }

    @Transactional
    public ViagemResponseDTO updateTrip(String id, ViagemUpdateRequestDTO dto, Usuario usuario) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        // Verifica se o motorista logado é o dono da viagem
        if (!viagem.getMotorista().getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Você não tem permissão para editar esta viagem.");
        }

        // Atualiza os campos permitidos
        if (dto.getDataPartida() != null)
            viagem.setDataPartida(dto.getDataPartida());

        if (dto.getValor() != null)
            viagem.setValor(dto.getValor());

        if (dto.getViagemStatus() != null)
            viagem.setViagemStatus(dto.getViagemStatus());

        viagem = viagemRepository.save(viagem);

        return new ViagemResponseDTO(
                viagem.getId(),
                viagem.getEstadoOrigem(),
                viagem.getCidadeOrigem(),
                viagem.getEstadoDestino(),
                viagem.getCidadeDestino(),
                viagem.getDataPartida(),
                viagem.getValor(),
                viagem.getVeiculo().getMarca() + " - " + viagem.getVeiculo().getModelo(),
                viagem.getCapacidadeDisponivel(),
                viagem.getViagemStatus()
        );
    }

    public ViagemCheckoutResponseDTO checkoutDetails(String viagemId) {
        Usuario user  = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        Passageiro passageiro = passageiroRepository.findById(usuario.getId()).orElseThrow(() -> new RuntimeException("Passageiro não associadonão associado"));
        Viagem viagem = viagemRepository.findById(viagemId).orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        return new ViagemCheckoutResponseDTO(
                viagem.getId(),
                viagem.getEstadoOrigem(),
                viagem.getCidadeOrigem(),
                viagem.getEstadoDestino(),
                viagem.getCidadeDestino(),
                viagem.getDataPartida(),
                viagem.getValor(),
                viagem.getVeiculo().getMarca() + " - " + viagem.getVeiculo().getModelo(),
                viagem.getCapacidadeDisponivel(),
                viagem.getMotorista().getUsuario().getUsername(),
                viagem.getCidadeOrigem() + " - " + viagem.getEstadoOrigem(),
                viagem.getCidadeDestino() + " - " + viagem.getEstadoDestino()

        );
    }
}
