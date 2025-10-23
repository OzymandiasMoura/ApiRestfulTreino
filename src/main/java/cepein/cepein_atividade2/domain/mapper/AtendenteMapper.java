package cepein.cepein_atividade2.domain.mapper;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;

public class AtendenteMapper
{
    public static AtendenteDto entityToDto(Atendente entity)
    {
        AtendenteDto dto = new AtendenteDto(entity.getIdAtendente(), entity.getNome(), entity.getCpf(), entity.getAtivo(), entity.getPedidos());
        return dto;
    }

    public static Atendente dtoToEntity(AtendenteDto dto)
    {
        Atendente entity = new Atendente(dto.getIdAtendente(), dto.getNome(), dto.getCpf(), dto.getAtivo(), dto.getPedidos());
        return entity;
    }
}
