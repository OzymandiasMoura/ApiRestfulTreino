package cepein.cepein_atividade2.domain.mapper;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.dto.PedidoDto;

public class PedidoMapper
{
    public static PedidoDto entityToDto(Pedido p)
    {
        PedidoDto dto = new PedidoDto(p.getIdPedido(), p.getDataAberturaPedido(), p.getAtendente(), p.getAberta(),  p.getDataFechamentoPedido());
        return dto;
    }

    public static Pedido dtoToEntity(PedidoDto dto)
    {
        Pedido p = new Pedido(dto.getIdPedido(), dto.getDataAberturaPedido(), dto.getAtendente(), dto.getAberta(),  dto.getDataFechamentoPedido());
        return p;
    }
}
