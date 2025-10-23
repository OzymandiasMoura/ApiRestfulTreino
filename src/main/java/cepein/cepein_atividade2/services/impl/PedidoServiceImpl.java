package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.mapper.AtendenteMapper;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.repositories.PedidoRepository;
import cepein.cepein_atividade2.services.AtendenteService;
import cepein.cepein_atividade2.services.PedidoService;
import cepein.cepein_atividade2.services.VendaService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService
{
    final String objectNotFoundMessage = "Pedido não encontrado!";
    @Autowired
    PedidoRepository repository;

    @Autowired
    VendaService vendaService;

    @Autowired
    AtendenteService atendenteService;

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
        Atendente atendente = dto.getAtendente();
        atendente.addPedido(PedidoMapper.dtoToEntity(dto));
        atendenteService.update(atendente.getIdAtendente(), AtendenteMapper.entityToDto(atendente));
        return repository.save(new Pedido(dto.getDataAberturaPedido(), dto.getAtendente()));
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
    public Pedido closeOrder(Integer id)
    {
        Pedido pedido = findById(id);

        pedido.setAberta(false);
        pedido.setDataFechamentoPedido(LocalDate.now());

        repository.save(pedido);

        return pedido;

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

    @Override
    public List<Produto> findProdutosInPedido(Integer id)
    {
        Pedido pedido = findById(id);
        List<Venda> vendas = vendaService.findByPedido(PedidoMapper.entityToDto(pedido));

        List<Produto> produtos = new ArrayList<>();
        vendas.forEach(venda -> produtos.add(venda.getProduto()));

        return produtos;
    }

//    @Override
//    public Pedido findLastPedido()
//    {
//        Optional<Pedido> obj = repository.findFirstByDataAberturaPedidoOrderByDesc();
//        return obj.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
//    }

    @Override
    public List<Pedido> findPedidosBetweenDates(LocalDate dataInicio, LocalDate dataFim)
    {
        Optional<List<Pedido>> list = Optional.of(repository.findAllByDataAberturaPedidoBetween(dataInicio, dataFim));
        return list.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
    }

    @Override
    public List<Pedido> findByAtendenteOrderByDataAberturaPedido(AtendenteDto atendente)
    {
        Optional<List<Pedido>> pedidos = Optional.of(repository.findByAtendenteOrderByDataAberturaPedido(AtendenteMapper.dtoToEntity(atendente)));
        return pedidos.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
    }

    @Override
    public List<Pedido> findByAtendenteOrderByDataAberturaPedidoDesc(AtendenteDto atendente)
    {
        Optional<List<Pedido>> pedidos = Optional.of(repository.findByAtendenteOrderByDataAberturaPedidoDesc(AtendenteMapper.dtoToEntity(atendente)));
        return pedidos.orElseThrow(() -> new ObjectNotFoundException(objectNotFoundMessage));
    }
}
