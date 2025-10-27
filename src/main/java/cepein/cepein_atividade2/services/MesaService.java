package cepein.cepein_atividade2.services;

import cepein.cepein_atividade2.domain.Mesa;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.MesaDto;
import java.util.List;

public interface MesaService
{
    Mesa createMesa(MesaDto mesa);

    Mesa updateMesa(Integer id, MesaDto mesaDto);

    void deleteMesa(Integer id);

    Mesa findById(Integer id);

    List<Mesa> findAll();

    Mesa findByAtendente(AtendenteDto atendenteDto);
}

