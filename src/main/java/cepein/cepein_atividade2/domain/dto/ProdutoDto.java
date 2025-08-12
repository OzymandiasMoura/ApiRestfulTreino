package cepein.cepein_atividade2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto
{
    private Integer idProduto;
    private String nome;
    private String barCode;
    private Double preco;
    private Boolean ativo;
}
