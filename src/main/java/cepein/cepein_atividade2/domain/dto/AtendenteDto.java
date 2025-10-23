package cepein.cepein_atividade2.domain.dto;

import cepein.cepein_atividade2.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendenteDto
{
    private Integer idAtendente;
    private String nome;
    private String cpf;
    private Boolean ativo;
    private List<Pedido> pedidos;
}
