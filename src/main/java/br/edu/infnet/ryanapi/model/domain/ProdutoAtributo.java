package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ProdutoAtributoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
}
