package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.DadosPessoaisRequestDTO;
import br.com.centralviagens.dtos.response.DadosPessoaisResponseDTO;
import br.com.centralviagens.exceptions.DadoDuplicadoException;
import br.com.centralviagens.models.DadosPessoais;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.repositories.DadosPessoaisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosPessoaisService {

    @Autowired
    private DadosPessoaisRepository dadosPessoaisRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public DadosPessoaisResponseDTO savePersonalData(DadosPessoaisRequestDTO dadosPessoaisRequest, Usuario usuario) {

        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setUsuario(usuario);
        dadosPessoais.setNome(dadosPessoaisRequest.getNome());
        dadosPessoais.setDataNascimento(dadosPessoaisRequest.getDataNascimento());
        dadosPessoais.setCpf(dadosPessoaisRequest.getCpf());
        dadosPessoais.setTelefone(dadosPessoaisRequest.getTelefone());

        dadosPessoais = dadosPessoaisRepository.save(dadosPessoais);

        DadosPessoaisResponseDTO responseDTO = new DadosPessoaisResponseDTO(
                dadosPessoais.getId(),
                dadosPessoais.getNome(),
                dadosPessoais.getDataNascimento(),
                dadosPessoais.getCpf(),
                dadosPessoais.getTelefone()
        );
        return responseDTO;
    }

}
