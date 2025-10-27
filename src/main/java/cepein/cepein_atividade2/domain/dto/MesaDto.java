package cepein.cepein_atividade2.domain.dto;

import cepein.cepein_atividade2.domain.Atendente;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MesaDto
{
    public Integer numMesa;
    public Atendente atendente;
}
