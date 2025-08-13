package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column(length = 100)
    @NonNull
    @NotBlank
    private String nome;

    @NonNull
    @NotBlank
    @Column(unique = true)
    private String barCode;

    @NonNull
    @NotBlank
    private Double preco;

    @NonNull
    @NotBlank
    private Boolean ativo;

    public Produto(String nome, String barCode, Double preco)
    {
        this.nome = nome;
        this.barCode = barCode;
        this.preco = preco;
        setAtivo(true);
    }
}
