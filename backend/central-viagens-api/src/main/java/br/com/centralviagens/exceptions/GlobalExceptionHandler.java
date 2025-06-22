package br.com.centralviagens.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Exceção personalizada: credenciais inválidas
    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<Map<String, String>> handleCredenciaisInvalidas(CredenciaisInvalidasException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // 2. Exceção personalizada: dado duplicado (usada nos services)
    @ExceptionHandler(DadoDuplicadoException.class)
    public ResponseEntity<Map<String, String>> handleDadoDuplicado(DadoDuplicadoException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 3. Violação de restrições do banco (ex: duplicidade na hora do save)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleViolacaoDeIntegridade(DataIntegrityViolationException ex) {
        Map<String, String> response = new HashMap<>();

        // Mensagem padrão
        String mensagem = "Dados já cadastrados. Verifique se username, email, CPF, CNH, placa ou telefone já foram usados.";

        // Opcional: analisar a mensagem da exceção para identificar o campo duplicado
        String lowerMsg = ex.getMostSpecificCause().getMessage().toLowerCase();
        if (lowerMsg.contains("username")) {
            mensagem = "Nome de usuário já está em uso.";
        } else if (lowerMsg.contains("email")) {
            mensagem = "Email já está em uso.";
        } else if (lowerMsg.contains("cpf")) {
            mensagem = "CPF já está em uso.";
        } else if (lowerMsg.contains("placa")) {
            mensagem = "Placa do veículo já está em uso.";
        } else if (lowerMsg.contains("cnh")) {
            mensagem = "Número de CNH já está em uso.";
        } else if (lowerMsg.contains("telefone")) {
            mensagem = "Telefone já está em uso.";
        }

        response.put("message", mensagem);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 4. Validações do Bean Validation (ex: @NotBlank, @Email etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        String erros = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        response.put("message", erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 5. Qualquer outra exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOutros(Exception ex) {
        ex.printStackTrace(); // Útil para debug em ambiente de desenvolvimento
        Map<String, String> response = new HashMap<>();
        response.put("message", "Erro interno. Tente novamente mais tarde.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
