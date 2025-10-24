package br.edu.infnet.ryanapi.exceptions;

public class EnderecoNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EnderecoNaoEncontradoException(String message) {
        super(message);
    }
}
