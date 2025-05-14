package br.com.centralviagens.services;

import br.com.centralviagens.dtos.request.UsuarioRequestDTO;
import br.com.centralviagens.dtos.response.UsuarioResponseDTO;
import br.com.centralviagens.models.Usuario;
import br.com.centralviagens.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO registerUser(UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(requestDTO.getUsername());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        usuario.setType(requestDTO.getType());
        usuario.setStatus(requestDTO.getStatus());

        usuario = usuarioRepository.save(usuario);

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getType(),
                usuario.getStatus(),
                usuario.getDadosPessoais().getTelefone()
        );
        return responseDTO;
    }

    @Transactional
    public Usuario registerUserEntity(UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(requestDTO.getUsername());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        usuario.setType(requestDTO.getType());
        usuario.setStatus(requestDTO.getStatus());

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> getAll() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                    usuario.getId(),
                    usuario.getUsername(),
                    usuario.getEmail(),
                    usuario.getPassword(),
                    usuario.getType(),
                    usuario.getStatus(),
                    usuario.getDadosPessoais().getTelefone()
            );
            return responseDTO;
        }).collect(Collectors.toList());
    }

    public UsuarioResponseDTO getByIdUser(String id) {
        return usuarioRepository.findById(id).map(usuario -> new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getType(),
                usuario.getStatus(),
                usuario.getDadosPessoais().getTelefone()
        )).orElseThrow(() -> new RuntimeException("usuario nao encontrado"));//trocar depois
    }
}
