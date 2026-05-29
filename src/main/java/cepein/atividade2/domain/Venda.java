package cepein.atividade2.domain;

import cepein.atividade2.domain.ids.VendaId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venda
{
    @EmbeddedId
    private VendaId idVenda;

    @ManyToOne
    @MapsId("idProduto")
    private Produto produto;

    @ManyToOne
    @MapsId("idPedido")
    private Pedido pedido;

    private int quantidade;

    private Double desconto;

    @Transient
    private Double precoFinal;

    public Venda(Pedido pedido, Produto produto, int qtde , Double desconto)
    {
        idVenda = new VendaId(produto.getIdProduto(), pedido.getIdPedido());
        setQuantidade(qtde);
        setPedido(pedido);
        setProduto(produto);
        setDesconto(desconto);
    }

    public void setDesconto(Double desconto)
    {
        this.desconto = desconto;
        this.precoFinal = produto.getPreco() - (produto.getPreco() * this.desconto);
    }
}
