package br.com.centralviagens.dtos.register;

import br.com.centralviagens.dtos.request.DadosPessoaisRequestDTO;
import br.com.centralviagens.dtos.request.EnderecoRequestDTO;
import br.com.centralviagens.dtos.request.PassageiroRequestDTO;
import br.com.centralviagens.dtos.request.UsuarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterPassageiroUsuarioDTO {

    private UsuarioRequestDTO usuario;

    private PassageiroRequestDTO passageiro;

    private DadosPessoaisRequestDTO dadosPessoais;

    private EnderecoRequestDTO endereco;
}
