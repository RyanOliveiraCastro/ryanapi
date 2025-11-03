package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.OperadorRequestDTO;
import br.edu.infnet.ryanapi.dto.OperadorRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "operador")
public class Operador extends Pessoa {

    @Size(min = 6, max = 10)
    @Column(name = "matricula", nullable = false, length = 10)
    private String matricula;

    @PastOrPresent
    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao;

    public Operador(OperadorRequestDTO operador, Endereco endereco) {
        this.setNome(operador.nome());
        this.setCpfCnpj(operador.cpfCnpj());
        this.setTelefone(operador.telefone());
        this.setEmail(operador.email());
        this.setAtivo(true);
        this.dataContratacao = operador.dataContratacao();
        this.matricula = operador.matricula();
        this.setDataCriacao(LocalDateTime.now());
    }

    public void alterar(OperadorRequestDTO operador) {
        this.setNome(operador.nome());
        this.setCpfCnpj( operador.cpfCnpj());
        this.setTelefone(operador.telefone());
        this.setEmail(operador.email());
        this.setDataNascimento(operador.dataNascimento());
        this.matricula = operador.matricula();
        this.dataContratacao = operador.dataContratacao();
    }

    public Operador(String nome, String cpfCnpj, String telefone, String email, LocalDate dataNascimento, String matricula, LocalDate dataContratacao) {
        super(email, dataNascimento, telefone, cpfCnpj, nome);
        this.matricula = matricula;
        this.dataContratacao = dataContratacao;
    }
}
