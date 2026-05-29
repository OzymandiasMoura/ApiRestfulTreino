package cepein.atividade2.services.impl;

import cepein.atividade2.domain.Atendente;
import cepein.atividade2.domain.Pedido;
import cepein.atividade2.domain.Venda;
import cepein.atividade2.domain.dto.PedidoDto;
import cepein.atividade2.domain.mapper.AtendenteMapper;
import cepein.atividade2.repositories.PedidoRepository;
import cepein.atividade2.repositories.VendaRepository;
import cepein.atividade2.services.exceptions.DataIntegrityException;
import cepein.atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PedidoServiceImplTest
{
    @InjectMocks
    PedidoServiceImpl service;

    @Mock
    PedidoRepository repository;

    @Mock
    VendaRepository vendaRepository;

    private static final String notFoundMessage = "Pedido não encontrado!";
    private static final String dataIntegrityMessage = "Pedido ja cadastrado";

    private Optional<Pedido> optional;
    private Pedido pedido;
    private PedidoDto dto;

    private final Integer id = 1;
    private final LocalDate date = LocalDate.of(2020, 1, 1);
    private final Atendente atendente = new Atendente("Pedro", "11111111111");
    private final Boolean ativo = true;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        optional = Optional.of(new Pedido(id, date, atendente, ativo, null));
        pedido = new Pedido(id, date, atendente, ativo, null);
        dto = new PedidoDto(id, date, atendente, ativo, null);
    }

    @Test
    void whenFindByIdThenSuccess()
    {
        Mockito.when(repository.findById(id)).thenReturn(optional);

        Pedido response = service.findById(id);

        assertNotNull(response);
        assertEquals(Pedido.class, response.getClass());
        assertEquals(id, response.getIdPedido());
        assertEquals(date, response.getDataAberturaPedido());
        assertEquals(atendente, response.getAtendente());
        assertEquals(true, response.getAberta());
        assertNull(response.getDataFechamentoPedido());
    }

    @Test
    void whenFindByIdThenException()
    {
        Mockito.when(repository.findById(id)).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findById(id);
        }
        catch(Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenCreateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(pedido);

        Pedido response = service.create(dto);

        assertNotNull(response);
        assertEquals(Pedido.class, response.getClass());
        assertEquals(id, response.getIdPedido());
        assertEquals(date, response.getDataAberturaPedido());
        assertEquals(atendente, response.getAtendente());
        assertEquals(true, response.getAberta());
        assertNull(response.getDataFechamentoPedido());
    }

    @Test
    void whenCreateThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.create(dto);
        }
        catch(Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void whenFindAllThenSuccess()
    {
        Mockito.when(repository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> response = service.findAll();

        assertNotNull(response);
        assertEquals(Pedido.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdPedido());
        assertEquals(date, response.get(0).getDataAberturaPedido());
        assertEquals(atendente, response.get(0).getAtendente());
        assertEquals(true, response.get(0).getAberta());
        assertNull(response.get(0).getDataFechamentoPedido());
        assertEquals(1, response.size());
    }

    @Test
    void whenUpdateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(pedido);

        Pedido response = service.update(dto);

        assertNotNull(response);
        assertEquals(Pedido.class, response.getClass());
        assertEquals(id, response.getIdPedido());
        assertEquals(date, response.getDataAberturaPedido());
        assertEquals(atendente, response.getAtendente());
        assertEquals(true, response.getAberta());
        assertNull(response.getDataFechamentoPedido());
    }

    @Test
    void whenUpdateThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.update(dto);
        }
        catch(Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void whenDeleteThenSuccess()
    {
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        service.delete(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    void whenDeleteThenException()
    {
        Mockito.doThrow(ObjectNotFoundException.class).when(repository).deleteById(Mockito.anyInt());
        try
        {
            service.delete(id);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenCloseOrderThenSuccess()
    {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(pedido));
        Mockito.when(repository.save(Mockito.any())).thenReturn(pedido);

        Pedido response = service.closeOrder(id);

        assertNotNull(response);
        assertEquals(Pedido.class, response.getClass());
        assertEquals(id, response.getIdPedido());
        assertEquals(date, response.getDataAberturaPedido());
        assertEquals(atendente, response.getAtendente());
        assertEquals(false, response.getAberta());
        assertNotNull(response.getDataFechamentoPedido());
    }

    @Test
    void whenCloseOrderThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));
        try
        {
            service.closeOrder(id);
        }
        catch(Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void validationOrder()
    {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.validationOrder(dto);
        }
        catch(Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void findProdutosInPedido()
    {
        Mockito.when(vendaRepository.findByPedido(Mockito.any())).thenReturn(List.of(Mockito.mock(Venda.class)));


    }

//    @Test
//    void findLastPedido()
//    {
//        Mockito.when(repository.findFirstByDataAberturaPedidoOrderByDesc()).thenReturn(Optional.of(pedido));
//
//        Pedido response = service.findLastPedido();
//
//        assertNotNull(response);
//        assertEquals(Pedido.class, response.getClass());
//        assertEquals(id, response.getIdPedido());
//        assertEquals(date, response.getDataAberturaPedido());
//        assertEquals(atendente, response.getAtendente());
//        assertEquals(true, response.getAberta());
//        assertNull(response.getDataFechamentoPedido());
//    }

    @Test
    void findPedidosBetweenDates()
    {
        Mockito.when(repository.findAllByDataAberturaPedidoBetween(Mockito.any(), Mockito.any())).thenReturn(List.of(pedido));

        List<Pedido> response = service.findPedidosBetweenDates(date, LocalDate.now());

        assertNotNull(response);
        assertEquals(Pedido.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdPedido());
        assertEquals(date, response.get(0).getDataAberturaPedido());
        assertEquals(atendente, response.get(0).getAtendente());
        assertEquals(true, response.get(0).getAberta());
        assertNull(response.get(0).getDataFechamentoPedido());
        assertEquals(1, response.size());
    }

    @Test
    void findByAtendenteOrderByDataAberturaPedido()
    {
        Mockito.when(repository.findByAtendenteOrderByDataAberturaPedido(Mockito.any())).thenReturn(List.of(pedido));

        List<Pedido> pedidos = service.findByAtendenteOrderByDataAberturaPedido(AtendenteMapper.entityToDto(atendente));

        assertNotNull(pedidos);
        assertEquals(Pedido.class, pedidos.get(0).getClass());
        assertEquals(id, pedidos.get(0).getIdPedido());
        assertEquals(date, pedidos.get(0).getDataAberturaPedido());
        assertEquals(atendente, pedidos.get(0).getAtendente());
        assertEquals(true, pedidos.get(0).getAberta());
        assertNull(pedidos.get(0).getDataFechamentoPedido());
        assertEquals(1, pedidos.size());
    }

    @Test
    void findByAtendenteOrderByDataAberturaPedidoDesc()
    {
        Mockito.when(repository.findByAtendenteOrderByDataAberturaPedidoDesc(Mockito.any())).thenReturn(List.of(pedido));

        List<Pedido> pedidos = service.findByAtendenteOrderByDataAberturaPedidoDesc(AtendenteMapper.entityToDto(atendente));

        assertNotNull(pedidos);
        assertEquals(Pedido.class, pedidos.get(0).getClass());
        assertEquals(id, pedidos.get(0).getIdPedido());
        assertEquals(date, pedidos.get(0).getDataAberturaPedido());
        assertEquals(atendente, pedidos.get(0).getAtendente());
        assertEquals(true, pedidos.get(0).getAberta());
        assertNull(pedidos.get(0).getDataFechamentoPedido());
        assertEquals(1, pedidos.size());
    }
}