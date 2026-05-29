package cepein.atividade2.services;

import cepein.atividade2.domain.Atendente;
import cepein.atividade2.domain.dto.AtendenteDto;
import java.util.List;

public interface AtendenteService
{
    Atendente findById(Integer id);
    Atendente create(AtendenteDto atendente);
    List<Atendente> findAll();
    Atendente update(Integer id, AtendenteDto atendenteDto);
    Atendente findByCpf(String cpf);
    void validationByCpf(AtendenteDto atendente);
    void delete(Integer id);
    Atendente softDelete(Integer id);
    List<Atendente> findByCpfOrIdAtendente(String cpf, Integer idAtendente);
}
