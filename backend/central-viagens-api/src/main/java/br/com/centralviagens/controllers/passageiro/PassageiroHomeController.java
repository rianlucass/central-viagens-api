package br.com.centralviagens.controllers.passageiro;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passageiro")
@CrossOrigin(origins = "http://localhost:3000")
public class PassageiroHomeController {
}
