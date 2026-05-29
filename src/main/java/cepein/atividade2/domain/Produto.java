package cepein.atividade2.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Produto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;

    @Column(length = 100, nullable = false)
    @NonNull
    private String nome;

    @NonNull
    @Column(unique = true,  nullable = false)
    private String barCode;

    @NonNull
    @Column(nullable = false)
    private Double preco;

    @NonNull
    @Column(nullable = false)
    private Boolean ativo;

    public Produto(String nome, String barCode, Double preco)
    {
        this.nome = nome;
        this.barCode = barCode;
        this.preco = preco;
        setAtivo(true);
    }
}
