package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.dto.VendaDto;
import cepein.cepein_atividade2.domain.ids.VendaId;
import cepein.cepein_atividade2.repositories.VendaRepository;
import cepein.cepein_atividade2.services.VendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VendaServiceImplTest
{
    @InjectMocks
    private VendaService service;
    @Mock
    private VendaRepository repository;

    private Atendente atendente = new Atendente("Pedro", "11111111111");
    private Produto produto = new Produto(1, "Geladeira", "2222222222222", 1550.50, true);
    private Pedido pedido = new Pedido(1, LocalDate.of(2020, 1, 1), atendente, true, null);
    private double desconto = 0.15;
    private int qtde = 2;
    private VendaId vendaId = new VendaId(produto.getIdProduto(),pedido.getIdPedido());

    private Optional<Venda> optional;
    private Venda venda;
    private VendaDto dto;


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
    }

    @Test
    void whenCreateThenException()
    {
    }

    @Test
    void whenFindAllThenSuccess()
    {
    }

    @Test
    void whenFindByIdThenSuccess()
    {
    }


    @Test
    void whenFindByIdThenException()
    {
    }

    @Test
    void whenUpdateThenSuccess()
    {
    }

    @Test
    void whenUpdateThenException()
    {
    }

    @Test
    void whenDeleteThenSuccess()
    {
    }

    @Test
    void whenDeleteThenException()
    {
    }

    @Test
    void whenFindByProductThenSuccess()
    {
    }

    @Test
    void whenFindByPedidoThenException()
    {
    }

    @Test
    void vendaValidation()
    {
    }
}