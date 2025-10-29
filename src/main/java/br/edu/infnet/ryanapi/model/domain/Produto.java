package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ProdutoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(max = 20)
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Size(max = 30)
    @Column(name = "marca", nullable = false)
    private String marca;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "preco", nullable = false, precision = 5, scale =  2)
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProdutoAtributo> atributos = new ArrayList<>();

    // relacionamento inverso opcional
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    private List<AgendamentoProduto> agendamentos = new ArrayList<>();

    public Produto(ProdutoRequestDTO produtoRequestDTO) {
        this.nome = produtoRequestDTO.nome();
        this.categoria = produtoRequestDTO.categoria();
        this.marca = produtoRequestDTO.marca();
        this.preco = produtoRequestDTO.preco();
        produtoRequestDTO.atributos()
                .forEach(atributoRequestDTO ->
                        adicionarAtributo(new ProdutoAtributo(atributoRequestDTO)));
    }

    public void adicionarAtributo(ProdutoAtributo atributo) {
        atributo.setProduto(this);
        this.atributos.add(atributo);
    }

    public void removerAtributo(ProdutoAtributo atributo) {
        atributo.setProduto(null);
        this.atributos.remove(atributo);
    }

    public void alterar(ProdutoRequestDTO produtoRequestDTO) {
        this.nome = produtoRequestDTO.nome();
        this.categoria = produtoRequestDTO.categoria();
        this.marca = produtoRequestDTO.marca();
        this.atributos.clear();
        produtoRequestDTO.atributos()
                .forEach(atributoRequestDTO ->
                        adicionarAtributo(new ProdutoAtributo(atributoRequestDTO)));
    }

}
