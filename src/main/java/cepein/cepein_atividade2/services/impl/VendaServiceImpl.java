package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.dto.VendaDto;
import cepein.cepein_atividade2.domain.ids.VendaId;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.domain.mapper.VendaMapper;
import cepein.cepein_atividade2.repositories.VendaRepository;
import cepein.cepein_atividade2.services.VendaService;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService
{
    private final String notFoundException = "Venda não encontrada!";


    @Autowired
    private VendaRepository repository;

    @Override
    public Venda create(VendaDto dto)
    {
        vendaValidation(dto.getProduto(), dto.getPedido(), dto);
        return repository.save(VendaMapper.dtoToEntity(dto));
    }

    @Override
    public List<Venda> findAll()
    {
        return repository.findAll();
    }

    @Override
    public Venda findById(VendaId id)
    {
        Optional<Venda> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(notFoundException));
    }

    @Override
    public Venda update(VendaDto dto)
    {
        vendaValidation(dto.getProduto(), dto.getPedido(), dto);
        return repository.save(VendaMapper.dtoToEntity(dto));
    }

    @Override
    public void delete(VendaId id)
    {
        repository.deleteById(id);
    }

    @Override
    public List<Venda> findByProduct(ProdutoDto produto)
    {
        List<Venda> list = repository.findByProduto(ProdutoMapper.dtoToEntity(produto));
        if(list.isEmpty())
        {
            throw new ObjectNotFoundException(notFoundException);
        }
        return list;
    }

    @Override
    public List<Venda> findByPedido(PedidoDto pedido)
    {
        List<Venda> list = repository.findByPedido(PedidoMapper.dtoToEntity(pedido));
        if(list.isEmpty())
        {
            throw new ObjectNotFoundException(notFoundException);
        }
        return list;
    }

    public void vendaValidation(Produto produto, Pedido pedido, VendaDto venda)
    {
        final String dataIntegrityExceptionMessage = "Venda já cadastrada!";
        Optional<List<Venda>> vendas = Optional.of(repository.findByProduto(produto));
        for (Venda v : vendas.get())
        {
            if(v.getPedido().equals(pedido) && v.getProduto().equals(produto) && !(venda.getIdVenda().equals(v.getIdVenda())))
            {
                throw new DataIntegrityException(dataIntegrityExceptionMessage);
            }
        }
    }
}
