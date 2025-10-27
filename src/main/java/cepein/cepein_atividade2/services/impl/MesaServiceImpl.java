package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Mesa;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.MesaDto;
import cepein.cepein_atividade2.domain.mapper.AtendenteMapper;
import cepein.cepein_atividade2.domain.mapper.MesaMapper;
import cepein.cepein_atividade2.repositories.MesaRepository;
import cepein.cepein_atividade2.services.MesaService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MesaServiceImpl implements MesaService
{
    @Autowired
    private MesaRepository repository;

    private final String notFound = "Mesa não encontrada!";

    @Override
    public Mesa createMesa(MesaDto mesa)
    {
        mesaValidation(mesa);

        return repository.save(MesaMapper.dtoToEntity(mesa));
    }

    @Override
    public Mesa updateMesa(Integer id, MesaDto mesaDto)
    {
        mesaValidation(mesaDto);

        Mesa mesa = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(notFound));
        mesa.setNumMesa(mesaDto.getNumMesa());
        mesa.setAtendente(mesaDto.getAtendente());

        return repository.save(mesa);
    }

    @Override
    public void deleteMesa(Integer id)
    {
        repository.deleteById(id);
    }

    @Override
    public Mesa findById(Integer id)
    {
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(notFound));
    }

    @Override
    public List<Mesa> findAll()
    {
        return repository.findAll();
    }

    @Override
    public Mesa findByAtendente(AtendenteDto atendenteDto)
    {
        return repository.findMesaByAtendente(AtendenteMapper.dtoToEntity(atendenteDto)).orElseThrow(()-> new ObjectNotFoundException(notFound));
    }

    private void mesaValidation(MesaDto mesaDto)
    {
        List<Mesa> mesas = repository.findAll();
        List<Atendente> atendentes = new ArrayList<>();
        mesas.forEach(mesa -> atendentes.add(mesa.getAtendente()));

        if(atendentes.contains(mesaDto.getAtendente()))
        {
            throw new DataIntegrityException("Atendente já possui mesa.");
        }
        if(mesaDto.getAtendente().getAtivo() == false)
        {
            throw new DataIntegrityException("Atendente desativado.");
        }
    }
}
