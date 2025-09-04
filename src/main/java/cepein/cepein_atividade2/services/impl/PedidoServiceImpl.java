package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.repositories.PedidoRepository;
import cepein.cepein_atividade2.services.PedidoService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService
{
    final String objectNotFoundMessage = "Pedido não encontrado!";
    @Autowired
    PedidoRepository repository;

    @Override
    public Pedido findById(Integer id)
    {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
    }

    @Override
    public Pedido create(PedidoDto dto)
    {
        validationOrder(dto);
        return repository.save(PedidoMapper.dtoToEntity(dto));
    }

    @Override
    public List<Pedido> findAll()
    {
        return repository.findAll();
    }

    @Override
    public Pedido update(PedidoDto dto)
    {
        validationOrder(dto);
        return repository.save(PedidoMapper.dtoToEntity(dto));
    }

    @Override
    public void delete(Integer id)
    {
        try
        {
            repository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new ObjectNotFoundException(objectNotFoundMessage);
        }
    }

    @Override
    public Pedido closeOrder(PedidoDto dto)
    {
        validationOrder(dto);
        Pedido obj = PedidoMapper.dtoToEntity(dto);
        obj.fecharPedido();

        repository.save(obj);
        return obj;
    }

    void validationOrder(PedidoDto dto)
    {
        final String dataIntegrityMessage = "Pedido já criado";
        Optional<Pedido> obj =  repository.findById(dto.getIdPedido());
        if(obj.isPresent() && !(obj.get().getIdPedido().equals(dto.getIdPedido())))
        {
            throw new DataIntegrityException(dataIntegrityMessage);
        }
    }
}
