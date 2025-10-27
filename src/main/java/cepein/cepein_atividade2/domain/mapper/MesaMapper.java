package cepein.cepein_atividade2.domain.mapper;

import cepein.cepein_atividade2.domain.Mesa;
import cepein.cepein_atividade2.domain.dto.MesaDto;

public class MesaMapper
{
    public static MesaDto entityToDto(Mesa entity)
    {
        return new MesaDto(entity.getNumMesa(), entity.getAtendente());
    }

    public static Mesa dtoToEntity(MesaDto dto)
    {
        return new Mesa(dto.getNumMesa(), dto.getAtendente());
    }
}
