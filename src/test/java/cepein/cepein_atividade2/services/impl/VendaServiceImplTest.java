package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.dto.VendaDto;
import cepein.cepein_atividade2.domain.ids.VendaId;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.repositories.VendaRepository;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaServiceImplTest
{
    @InjectMocks
    private VendaServiceImpl service;
    @Mock
    private VendaRepository repository;

    private Atendente atendente = new Atendente("Pedro", "11111111111");
    private Produto produto = new Produto(1, "Geladeira", "2222222222222", 1550.50, true);
    private Pedido pedido = new Pedido(1, LocalDate.of(2020, 1, 1), atendente, true, null);
    private double desconto = 0.15;
    private int qtde = 2;
    private VendaId vendaId = new VendaId(produto.getIdProduto(),pedido.getIdPedido());
    private double precoFinal = produto.getPreco() - (produto.getPreco() * desconto);

    private Optional<Venda> optional;
    private Venda venda;
    private VendaDto dto;

    public static final String notFoundMessage = "Venda não encontrada!";
    public static final String dataIntegrityMessage = "Venda já cadastrada!";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        venda = new Venda(pedido, produto, qtde, desconto);
        optional = Optional.of(venda);
        dto = new VendaDto(vendaId, produto, pedido, qtde, desconto, venda.getPrecoFinal());
    }

    @Test
    void whenCreateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(venda);

        Venda response =  service.create(dto);

        assertNotNull(response);
        assertEquals(Venda.class, response.getClass());
        assertEquals(vendaId, response.getIdVenda());
        assertEquals(pedido, response.getPedido());
        assertEquals(produto, response.getProduto());
        assertEquals(desconto, response.getDesconto());
        assertEquals(qtde, response.getQuantidade());
        assertEquals(precoFinal, response.getPrecoFinal());

    }

    @Test
    void whenCreateThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.create(dto);
        }
        catch (Exception e)
        {
            assertEquals(DataIntegrityException.class, e.getClass());
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertNotNull(e);
        }
    }

    @Test
    void whenFindAllThenSuccess()
    {
        Mockito.when(repository.findAll()).thenReturn(List.of(venda));

        List<Venda> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Venda.class, response.get(0).getClass());
        assertEquals(vendaId, response.get(0).getIdVenda());
        assertEquals(pedido, response.get(0).getPedido());
        assertEquals(produto, response.get(0).getProduto());
        assertEquals(qtde, response.get(0).getQuantidade());
        assertEquals(desconto, response.get(0).getDesconto());
        assertEquals(precoFinal, response.get(0).getPrecoFinal());

    }

    @Test
    void whenFindByIdThenSuccess()
    {
        Mockito.when(repository.findById(vendaId)).thenReturn(optional);

        Venda response = service.findById(vendaId);

        assertNotNull(response);
        assertEquals(Venda.class, response.getClass());
        assertEquals(venda, response);
        assertEquals(vendaId, response.getIdVenda());
        assertEquals(pedido, response.getPedido());
        assertEquals(produto, response.getProduto());
        assertEquals(desconto, response.getDesconto());
        assertEquals(qtde, response.getQuantidade());
        assertEquals(precoFinal, response.getPrecoFinal());
    }


    @Test
    void whenFindByIdThenException()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findById(vendaId);
        }
        catch (Exception e)
        {
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertNotNull(e);
        }
    }

    @Test
    void whenUpdateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(venda);

        Venda response = service.update(dto);

        assertNotNull(response);
        assertEquals(Venda.class, response.getClass());
        assertEquals(vendaId, response.getIdVenda());
        assertEquals(pedido, response.getPedido());
        assertEquals(produto, response.getProduto());
        assertEquals(desconto, response.getDesconto());
        assertEquals(qtde, response.getQuantidade());
    }

    @Test
    void whenUpdateThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(venda);

        try
        {
            service.update(dto);
        }
        catch (Exception e)
        {
            assertEquals(DataIntegrityException.class, e.getClass());
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertNotNull(e);
        }
    }

    @Test
    void whenDeleteThenSuccess()
    {
        Mockito.doNothing().when(repository).delete(Mockito.any());
        service.delete(vendaId);
        Mockito.verify(repository, Mockito.times(1)).deleteById(vendaId);
    }

    @Test
    void whenFindByProductThenSuccess()
    {
        Mockito.when(repository.findByProduto(Mockito.any())).thenReturn(List.of(venda));

        List<Venda> response = service.findByProduct(ProdutoMapper.entityToDto(produto));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Venda.class, response.get(0).getClass());
        assertEquals(vendaId, response.get(0).getIdVenda());
        assertEquals(pedido, response.get(0).getPedido());
        assertEquals(produto, response.get(0).getProduto());
        assertEquals(desconto, response.get(0).getDesconto());
        assertEquals(qtde, response.get(0).getQuantidade());
        assertEquals(precoFinal, response.get(0).getPrecoFinal());
    }

    @Test
    void whenFindByProdutoThenException()
    {
        Mockito.when(repository.findByProduto(Mockito.any())).thenReturn(List.of(venda));

        try
        {
            List<Venda> response = service.findByProduct(ProdutoMapper.entityToDto(produto));
        }
        catch (Exception e)
        {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(notFoundMessage, e.getMessage());
            assertNotNull(e);
        }
    }

    @Test
    void whenFindByPedidoThenSuccess()
    {
        Mockito.when(repository.findByPedido(Mockito.any())).thenReturn(List.of(venda));

        List<Venda> response = service.findByPedido(PedidoMapper.entityToDto(pedido));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Venda.class, response.get(0).getClass());
        assertEquals(vendaId, response.get(0).getIdVenda());
        assertEquals(pedido, response.get(0).getPedido());
        assertEquals(produto, response.get(0).getProduto());
        assertEquals(desconto, response.get(0).getDesconto());
        assertEquals(qtde, response.get(0).getQuantidade());
        assertEquals(precoFinal, response.get(0).getPrecoFinal());
    }

    @Test
    void whenFindByPedidoThenException()
    {
        Mockito.when(repository.findByPedido(Mockito.any())).thenReturn(List.of(venda));

        try
        {
            List<Venda> response = service.findByPedido(PedidoMapper.entityToDto(pedido));
        }
        catch (Exception e)
        {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(notFoundMessage, e.getMessage());
            assertNotNull(e);
        }
    }

    @Test
    void vendaValidation()
    {
    }
}