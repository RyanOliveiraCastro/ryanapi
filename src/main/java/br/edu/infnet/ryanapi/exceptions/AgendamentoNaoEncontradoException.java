package br.edu.infnet.ryanapi.exceptions;

public class AgendamentoNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AgendamentoNaoEncontradoException(String message) {
        super(message);
    }
}
