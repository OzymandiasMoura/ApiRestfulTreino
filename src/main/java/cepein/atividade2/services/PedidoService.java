package cepein.atividade2.services;

import cepein.atividade2.domain.Pedido;
import cepein.atividade2.domain.Produto;
import cepein.atividade2.domain.dto.AtendenteDto;
import cepein.atividade2.domain.dto.PedidoDto;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService
{
    Pedido findById(Integer id);
    Pedido create(PedidoDto dto);
    List<Pedido> findAll();
    Pedido update(PedidoDto dto);
    void delete(Integer id);
    Pedido closeOrder(Integer id);
    List<Produto> findProdutosInPedido(Integer id);
    List<Pedido> findPedidosBetweenDates(LocalDate dataInicio, LocalDate dataFim);
    List<Pedido> findByAtendenteOrderByDataAberturaPedido(AtendenteDto atendente);
    List<Pedido> findByAtendenteOrderByDataAberturaPedidoDesc(AtendenteDto atendente);
}
