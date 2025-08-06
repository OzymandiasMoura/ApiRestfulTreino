package cepein.cepein_atividade2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendenteDto
{
    private Integer idAtendente;
    private String nome;
    private String cpf;
}
