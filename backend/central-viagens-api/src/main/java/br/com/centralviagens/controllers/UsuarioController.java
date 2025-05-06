package br.com.centralviagens.controllers;

import br.com.centralviagens.dtos.response.UsuarioResponseDTO;
import br.com.centralviagens.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> getAllUsers() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO getByIdUser(@PathVariable String id) {
        return usuarioService.getByIdUser(id);
    }


}
