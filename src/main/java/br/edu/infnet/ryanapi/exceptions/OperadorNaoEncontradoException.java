package br.edu.infnet.ryanapi.exceptions;

public class OperadorNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OperadorNaoEncontradoException(String message) {
        super(message);
    }
}
