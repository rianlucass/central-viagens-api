package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.DadosPessoaisRequestDTO;
import br.com.centralviagens.dtos.request.EnderecoRequestDTO;
import br.com.centralviagens.dtos.request.PassageiroRequestDTO;
import br.com.centralviagens.dtos.request.UsuarioRequestDTO;
import br.com.centralviagens.dtos.response.DadosPessoaisResponseDTO;
import br.com.centralviagens.dtos.response.EnderecoResponseDTO;
import br.com.centralviagens.dtos.response.PassageiroResponseDTO;
import br.com.centralviagens.models.Passageiro;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.repositories.PassageiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @Autowired
    private EnderecoService enderecoService;

    @Transactional
    public PassageiroResponseDTO registerPassenger(PassageiroRequestDTO passageiroRequest, UsuarioRequestDTO usuarioRequest, DadosPessoaisRequestDTO dadosPessoaisRequest, EnderecoRequestDTO enderecoRequest) {

        Usuario usuario = usuarioService.registerUserEntity(usuarioRequest);
        DadosPessoaisResponseDTO dadosPessoais = dadosPessoaisService.savePersonalData(dadosPessoaisRequest, usuario);
        EnderecoResponseDTO endereco = enderecoService.saveAddress(enderecoRequest, usuario);

        Passageiro passageiro = new Passageiro();
        passageiro.setUsuario(usuario);

        passageiro = passageiroRepository.save(passageiro);
        PassageiroResponseDTO responseDTO = new PassageiroResponseDTO(
                passageiro.getId(),
                passageiro.getUsuario().getUsername(),
                dadosPessoais.cpf(),
                endereco.cep(),
                passageiro.getViagensCompradas()
        );
        return responseDTO;
    }

    public List<PassageiroResponseDTO> getAllPassenger() {
        return passageiroRepository.findAll().stream().map(passageiro -> {
            PassageiroResponseDTO responseDTO = new PassageiroResponseDTO(
                    passageiro.getId(),
                    passageiro.getUsuario().getUsername(),
                    passageiro.getUsuario().getEndereco().getCep(),
                    passageiro.getUsuario().getDadosPessoais().getCpf(),
                    passageiro.getViagensCompradas()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }

}
