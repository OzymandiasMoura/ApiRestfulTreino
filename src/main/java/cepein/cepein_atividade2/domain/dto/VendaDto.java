package cepein.cepein_atividade2.domain.dto;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.ids.VendaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendaDto
{
    private VendaId idVenda;
    private Produto produto;
    private Pedido pedido;
    private int quantidade;
    private Double desconto;
    private Double precoFinal;
}
