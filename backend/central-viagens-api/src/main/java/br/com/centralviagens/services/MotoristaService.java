package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.DadosPessoaisRequestDTO;
import br.com.centralviagens.dtos.request.EnderecoRequestDTO;
import br.com.centralviagens.dtos.request.MotoristaRequestDTO;
import br.com.centralviagens.dtos.request.UsuarioRequestDTO;
import br.com.centralviagens.dtos.response.DadosPessoaisResponseDTO;
import br.com.centralviagens.dtos.response.EnderecoResponseDTO;
import br.com.centralviagens.dtos.response.MotoristaResponseDTO;
import br.com.centralviagens.models.DadosPessoais;
import br.com.centralviagens.models.Motorista;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.repositories.MotoristaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @Transactional
    public MotoristaResponseDTO registerDriver(MotoristaRequestDTO motoristaRequest, UsuarioRequestDTO usuarioRequest, DadosPessoaisRequestDTO dadosPessoaisRequest, EnderecoRequestDTO enderecoRequest) {

        Usuario usuario = usuarioService.registerUserEntity(usuarioRequest);
        DadosPessoaisResponseDTO dadosPessoais = dadosPessoaisService.savePersonalData(dadosPessoaisRequest, usuario);
        EnderecoResponseDTO endereco = enderecoService.saveAddress(enderecoRequest, usuario);

        Motorista motorista = new Motorista();
        motorista.setUsuario(usuario);
        motorista.setCnh_categoria(motoristaRequest.getCnh_categoria());
        motorista.setCnh_numero(motoristaRequest.getCnh_numero());
        motorista.setValidade_cnh(motoristaRequest.getValidade_cnh());
        motorista = motoristaRepository.save(motorista);

        MotoristaResponseDTO responseDTO = new MotoristaResponseDTO(
                motorista.getId(),
                motorista.getCnh_categoria(),
                motorista.getCnh_numero(),
                motorista.getValidade_cnh(),
                motorista.getUsuario().getUsername(),
                dadosPessoais.cpf(),
                endereco.cep(),
                motorista.getViagensCriadas(),
                motorista.getVeiculos()
        );
        return responseDTO;
    }

}
