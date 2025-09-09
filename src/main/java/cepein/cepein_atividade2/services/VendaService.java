package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.dto.VendaDto;
import cepein.cepein_atividade2.domain.ids.VendaId;

import java.util.List;

public interface VendaService
{
    Venda create(VendaDto dto);
    List<Venda> findAll();
    Venda findById(VendaId id);
    Venda update(VendaDto dto);
    void delete(VendaDto dto);
    List<Venda> findByProduct(ProdutoDto produto);
    List<Venda> findByPedido(PedidoDto pedido);
}
