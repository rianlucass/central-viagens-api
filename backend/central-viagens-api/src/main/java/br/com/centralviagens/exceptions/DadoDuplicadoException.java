package br.com.centralviagens.exceptions;

public class DadoDuplicadoException extends RuntimeException{
    public DadoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
