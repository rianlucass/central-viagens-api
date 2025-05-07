package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.EnderecoRequestDTO;
import br.com.centralviagens.dtos.response.EnderecoResponseDTO;
import br.com.centralviagens.models.Endereco;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public EnderecoResponseDTO saveAddress(EnderecoRequestDTO enderecoRequest, Usuario usuario) {

        Endereco endereco = new Endereco();
        endereco.setUsuario(usuario);
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setRua(enderecoRequest.getRua());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setNumero(enderecoRequest.getNumero());

        endereco = enderecoRepository.save(endereco);

        EnderecoResponseDTO responseDTO = new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getCep(),
                endereco.getRua(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
        return responseDTO;
    }

    public List<EnderecoResponseDTO> getAllAddress() {
        return enderecoRepository.findAll().stream().map(endereco -> {
            EnderecoResponseDTO responseDTO = new EnderecoResponseDTO(
                    endereco.getId(),
                    endereco.getEstado(),
                    endereco.getCidade(),
                    endereco.getCep(),
                    endereco.getRua(),
                    endereco.getBairro(),
                    endereco.getComplemento(),
                    endereco.getNumero()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }

}
