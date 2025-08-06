//package cepein.cepein_atividade2.domain;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class ProdutoNoPedido
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("idPedido")
//    private Pedido pedido;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("idProduto")
//    private Produto produto;
//
//    private Double desconto;
//
//    @Transient
//    private Double precoFinal;
//
//    public ProdutoNoPedido(Pedido pedido, Produto produto, Double desconto)
//    {
//        this.pedido = pedido;
//        this.produto = produto;
//        setDesconto(desconto);
//    }
//
//    public void setDesconto(Double desconto)
//    {
//        this.desconto = desconto;
//        this.precoFinal = produto.getPreco() - (produto.getPreco() * this.desconto);
//    }
//}
