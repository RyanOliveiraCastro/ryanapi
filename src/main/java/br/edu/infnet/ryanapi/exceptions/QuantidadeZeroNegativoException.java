package br.edu.infnet.ryanapi.exceptions;


public class QuantidadeZeroNegativoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public QuantidadeZeroNegativoException(String message) {
        super(message);
    }
}
