package br.com.centralviagens.exceptions;

public class CredenciaisInvalidasException extends RuntimeException{

    public CredenciaisInvalidasException(String mensagem) {
        super(mensagem);
    }

}
