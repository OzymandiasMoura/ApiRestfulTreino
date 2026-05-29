package cepein.atividade2.domain.dto;

import cepein.atividade2.domain.Atendente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto
{
    private Integer idPedido;
    private LocalDate dataAberturaPedido;
    private Atendente atendente;
    private Boolean aberta;
    private LocalDate dataFechamentoPedido;
}
