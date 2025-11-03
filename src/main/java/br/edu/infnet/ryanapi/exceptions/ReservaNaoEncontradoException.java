package br.edu.infnet.ryanapi.exceptions;

public class ReservaNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReservaNaoEncontradoException(String message) {
        super(message);
    }
}
