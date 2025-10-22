package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ProdutoAtributoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto_atributo")
public class ProdutoAtributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "valor", nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ProdutoAtributo(ProdutoAtributoRequestDTO produtoAtributoRequestDTO) {
        this.nome = produtoAtributoRequestDTO.nome();
        this.valor = produtoAtributoRequestDTO.valor();
    }
}
