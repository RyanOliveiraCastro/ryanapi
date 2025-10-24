package br.edu.infnet.ryanapi.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
