package br.edu.infnet.ryanapi.exceptions;

public class AgendamentoSemProdutoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AgendamentoSemProdutoException(String message) {
        super(message);
    }
}
