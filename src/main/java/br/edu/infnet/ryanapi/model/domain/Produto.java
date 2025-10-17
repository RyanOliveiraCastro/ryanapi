package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String nome;
    private String categoria;
    private String marca;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProdutoAtributo> atributos = new ArrayList<>();

    // relacionamento inverso opcional
    @OneToMany(mappedBy = "produto")
    private List<AgendamentoProduto> agendamentos = new ArrayList<>();

    public void adicionarAtributo(ProdutoAtributo atributo) {
        atributo.setProduto(this);
        this.atributos.add(atributo);
    }

    public void removerAtributo(ProdutoAtributo atributo) {
        atributo.setProduto(null);
        this.atributos.remove(atributo);
    }

}
