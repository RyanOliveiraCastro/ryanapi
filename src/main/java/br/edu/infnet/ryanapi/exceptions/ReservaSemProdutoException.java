package br.edu.infnet.ryanapi.exceptions;

public class ReservaSemProdutoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReservaSemProdutoException(String message) {
        super(message);
    }
}
