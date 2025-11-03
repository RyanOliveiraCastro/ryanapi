package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ProdutoAtributoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "produto_atributo")
public class ProdutoAtributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @Column(name = "nome", nullable = false)
    private String nome;
    @Size(max = 30)
    @Column(name = "valor", nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ProdutoAtributo(ProdutoAtributoRequestDTO produtoAtributoRequestDTO) {
        this.nome = produtoAtributoRequestDTO.nome();
        this.valor = produtoAtributoRequestDTO.valor();
    }

    public ProdutoAtributo(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format(
                "ProdutoAtributo [ID: %d | Nome: %s | Valor: %s | Produto: %s]",
                id,
                nome,
                valor,
                produto != null ? produto.getNome() : "N/A"
        );
    }

}
