package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.PedidoDto;

import java.util.List;

public interface PedidoService
{
    Pedido findById(Integer id);
    Pedido create(PedidoDto dto);
    List<Pedido> findAll();
    Pedido update(PedidoDto dto);
    void delete(Integer id);
    Pedido closeOrder(PedidoDto dto);
    List<Produto> findProdutosInPedido(PedidoDto dto);
    Pedido findLastPedido(Integer id);
}
