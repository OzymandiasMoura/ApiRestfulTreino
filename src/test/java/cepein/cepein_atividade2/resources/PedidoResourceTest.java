package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.mapper.AtendenteMapper;
import cepein.cepein_atividade2.services.AtendenteService;
import cepein.cepein_atividade2.services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PedidoResourceTest
{
    private final Integer id = 1;
    private final LocalDate dataPedido = LocalDate.of(2020, 01, 01);
    private final Atendente atendente = new Atendente("Pedro", "11111111111");
    private final Boolean aberto  = true;
    private final LocalDate fechamento = null;
    private Pedido pedido;
    private PedidoDto dto;

    @InjectMocks
    private PedidoResource resource;

    @Mock
    private PedidoService service;

    @Mock
    private AtendenteService atendenteService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        pedido =  new Pedido(id,  dataPedido, atendente, aberto, fechamento);
        dto =  new PedidoDto(id,  dataPedido, atendente, aberto, fechamento);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void whenFindByIdThenOk()
    {
        Mockito.when(service.findById(id)).thenReturn(pedido);

        ResponseEntity<PedidoDto> response = resource.findById(id);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().getClass());
        assertEquals(id, response.getBody().getIdPedido());
        assertEquals(dataPedido, response.getBody().getDataAberturaPedido());
        assertEquals(atendente.getClass(), response.getBody().getAtendente().getClass());
        assertEquals(aberto, response.getBody().getAberta());
        assertNull(response.getBody().getDataFechamentoPedido());

    }

    @Test
    void whenFindAllThenOk()
    {
        Mockito.when(service.findAll()).thenReturn(List.of(pedido));

        ResponseEntity<List<PedidoDto>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().get(0).getClass());
        assertEquals(id, response.getBody().get(0).getIdPedido());
        assertEquals(dataPedido, response.getBody().get(0).getDataAberturaPedido());
        assertEquals(atendente.getClass(), response.getBody().get(0).getAtendente().getClass());
        assertEquals(aberto, response.getBody().get(0).getAberta());
        assertNull(response.getBody().get(0).getDataFechamentoPedido());

    }

    @Test
    void whenCreateThenCreated()
    {
        Mockito.when(service.create(Mockito.any())).thenReturn(pedido);

        ResponseEntity<PedidoDto> response = resource.create(dto);

        assertNotNull(response);
        assertNotNull(response.getHeaders().get("location"));
        assertNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void update()
    {
        Mockito.when(service.update(Mockito.any())).thenReturn(pedido);

        ResponseEntity<PedidoDto> response = resource.update(id, dto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().getClass());
        assertEquals(id, response.getBody().getIdPedido());
        assertEquals(dataPedido, response.getBody().getDataAberturaPedido());
        assertEquals(atendente.getClass(), response.getBody().getAtendente().getClass());
        assertEquals(aberto, response.getBody().getAberta());
        assertNull(response.getBody().getDataFechamentoPedido());
    }

    @Test
    void delete()
    {
        Mockito.doNothing().when(service).delete(Mockito.anyInt());

        ResponseEntity<PedidoDto> response = resource.delete(id);

        assertNotNull(response);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        Mockito.verify(service, Mockito.times(1)).delete(id);
    }

    @Test
    void closeOrder()
    {
        Mockito.when(service.closeOrder(Mockito.any())).thenReturn(pedido);

        ResponseEntity<PedidoDto> response = resource.closeOrder(id, dto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().getClass());
        assertEquals(id, response.getBody().getIdPedido());
        assertEquals(dataPedido, response.getBody().getDataAberturaPedido());
        assertEquals(atendente.getClass(), response.getBody().getAtendente().getClass());
        assertEquals(false, response.getBody().getAberta());
        assertNotNull(response.getBody().getDataFechamentoPedido());
    }

    @Test
    void findLastPedido()
    {
        Mockito.when(service.findLastPedido()).thenReturn(pedido);

        ResponseEntity<PedidoDto> response = resource.findLastPedido();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void findPedidosBetweenDates()
    {
        Mockito.when(service.findPedidosBetweenDates(Mockito.any(), Mockito.any())).thenReturn(List.of(pedido));

        ResponseEntity<List<PedidoDto>> response = resource.findPedidosBetweenDates("01012025", "31012025");

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().get(0).getClass());
        assertEquals(LocalDate.of(2020,01,01), response.getBody().get(0).getDataAberturaPedido());
    }

    @Test
    void findByAtendenteOrderByDataAberturaPedido()
    {
        Mockito.when(service.findByAtendenteOrderByDataAberturaPedido(Mockito.any())).thenReturn(List.of(pedido));

        Integer idAtendente = atendente.getIdAtendente();
        ResponseEntity<List<PedidoDto>> response  = resource.findByAtendenteOrderByDataAberturaPedido(idAtendente);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().get(0).getClass());
    }

    @Test
    void findByAtendenteOrderByDataAberturaPedidoDesc()
    {
        Mockito.when(service.findByAtendenteOrderByDataAberturaPedidoDesc(Mockito.mock(AtendenteDto.class))).thenReturn(List.of(pedido));

        Integer idAtendente = atendente.getIdAtendente();
        ResponseEntity<List<PedidoDto>> response  = resource.findByAtendenteOrderByDataAberturaPedidoDesc(idAtendente);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PedidoDto.class, response.getBody().get(0).getClass());
    }
}