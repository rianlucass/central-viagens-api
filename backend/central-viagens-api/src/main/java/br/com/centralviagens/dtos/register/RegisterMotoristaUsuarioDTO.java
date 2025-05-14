package br.com.centralviagens.dtos.register;

import br.com.centralviagens.dtos.request.DadosPessoaisRequestDTO;
import br.com.centralviagens.dtos.request.EnderecoRequestDTO;
import br.com.centralviagens.dtos.request.MotoristaRequestDTO;
import br.com.centralviagens.dtos.request.UsuarioRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterMotoristaUsuarioDTO {
    private UsuarioRequestDTO usuario;
    private MotoristaRequestDTO motorista;
    private DadosPessoaisRequestDTO dadosPessoais;
    private EnderecoRequestDTO endereco;
}
