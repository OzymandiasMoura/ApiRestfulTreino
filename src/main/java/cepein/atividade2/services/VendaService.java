package cepein.atividade2.services;

import cepein.atividade2.domain.Venda;
import cepein.atividade2.domain.dto.PedidoDto;
import cepein.atividade2.domain.dto.ProdutoDto;
import cepein.atividade2.domain.dto.VendaDto;
import cepein.atividade2.domain.ids.VendaId;

import java.util.List;

public interface VendaService
{
    Venda create(VendaDto dto);
    List<Venda> findAll();
    Venda findById(VendaId id);
    Venda update(VendaDto dto);
    void delete(VendaId id);
    List<Venda> findByProduct(ProdutoDto produto);
    List<Venda> findByPedido(PedidoDto pedido);
    Venda findByProdutoAndPedido(ProdutoDto produto, PedidoDto pedido);
}
