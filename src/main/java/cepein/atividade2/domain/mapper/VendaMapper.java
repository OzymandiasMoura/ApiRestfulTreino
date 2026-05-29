package cepein.atividade2.domain.mapper;

import cepein.atividade2.domain.Venda;
import cepein.atividade2.domain.dto.VendaDto;

public class VendaMapper
{
    public static VendaDto entityToDto(Venda v)
    {
        VendaDto dto = new VendaDto(v.getIdVenda(), v.getProduto(), v.getPedido(), v.getQuantidade(), v.getDesconto(), v.getPrecoFinal());
        return dto;
    }

    public static Venda dtoToEntity(VendaDto dto)
    {
        Venda entity = new Venda(dto.getIdVenda(), dto.getProduto(), dto.getPedido(), dto.getQuantidade(), dto.getDesconto(), dto.getPrecoFinal());
        return entity;
    }
}
