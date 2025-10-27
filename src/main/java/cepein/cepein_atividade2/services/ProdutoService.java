package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import java.util.List;

public interface ProdutoService
{
    Produto findById(Integer id);
    Produto create(ProdutoDto atendente);
    List<Produto> findAll();
    Produto update(ProdutoDto atendente);
    Produto findByBarCode(String barCode);
    void validationByBarCode (ProdutoDto atendente);
    void delete(Integer id);
    Produto softDelete(ProdutoDto atendente);
    List<Pedido> findPedidosInProdutos(ProdutoDto dto);
    List<Produto> findByPrecoLessThan(Double preco);
    List<Produto> findByPrecoLessThanEqual(Double preco);
    List<Produto> findByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoGreaterThanEqual(Double preco);
    List<Produto> findByPrecoIn(List<Double> preco);
}
