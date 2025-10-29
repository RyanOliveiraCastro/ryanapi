package br.edu.infnet.ryanapi.exceptions.handler;

import br.edu.infnet.ryanapi.exceptions.AgendamentoSemProdutoException;
import br.edu.infnet.ryanapi.exceptions.ProdutoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> mapa = new HashMap<String, String>();

        e.getBindingResult().getAllErrors().forEach((erro) -> {
            String nomeCampo = ((FieldError) erro).getField();
            String mensagemErro = erro.getDefaultMessage();
            mapa.put(nomeCampo, mensagemErro);
        });

        return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e) {
        Map<String, String> mapa = new HashMap<String, String>();

        mapa.put("timestamp", LocalDateTime.now().toString());
        mapa.put("status", HttpStatus.NOT_FOUND.toString());
        mapa.put("error", e.getMessage());
        mapa.put("detail", "O produto solicitado não foi encontrado na base de dados!");

        return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AgendamentoSemProdutoException.class)
    public ResponseEntity<Map<String, String>> handleAgendamentoSemProdutoException(AgendamentoSemProdutoException e) {
        Map<String, String> mapa = new HashMap<String, String>();

        mapa.put("timestamp", LocalDateTime.now().toString());
        mapa.put("status", HttpStatus.BAD_REQUEST.toString());
        mapa.put("error", e.getMessage());
        mapa.put("detail", "O agendamento não tem produtos!");

        return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, String> mapa = new HashMap<String, String>();

        mapa.put("timestamp", LocalDateTime.now().toString());
        mapa.put("status", HttpStatus.BAD_REQUEST.toString());
        mapa.put("error", e.getMessage());
        mapa.put("detail", "Algum argumento inválido foi fornecido para essa operação!");

        return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Map<String, String> mapa = new HashMap<String, String>();

        mapa.put("timestamp", LocalDateTime.now().toString());
        mapa.put("status", HttpStatus.CONFLICT.toString());
        mapa.put("error", e.getMessage());
        mapa.put("detail", "Problema no momento de criar um recurso duplicado!");

        return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> mapa = new HashMap<String, String>();

        mapa.put("timestamp", LocalDateTime.now().toString());
        mapa.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        mapa.put("error", e.getMessage());
        mapa.put("detail", "Ocorreu um erro interno no servidor!");

        return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
