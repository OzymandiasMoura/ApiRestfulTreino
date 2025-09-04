package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
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
    List<Venda> findByProduct(Produto produto);
    List<Venda> findByPedido(Pedido pedido);
}
