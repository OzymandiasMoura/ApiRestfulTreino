package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import java.util.List;

public interface AtendenteService
{
    Atendente findById(Integer id);
    Atendente create(AtendenteDto atendente);
    List<Atendente> findAll();
    Atendente update(AtendenteDto atendente);
    Atendente findByCpf(AtendenteDto atendente);
    void validationByCpf(AtendenteDto atendente);
}
