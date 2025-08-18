package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoResourceTest
{
    @InjectMocks
    ProdutoResource resource;

    @Mock
    ProdutoService service;


    private ProdutoDto produtoDto;
    private Produto produto;

    private final static Integer id = 1;
    private final static String nome = "Geladeira";
    private final static String barCode = "1111111111111";
    private final static Double preco = 1550.50;
    private final static Boolean ativo = true;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        produto = new Produto(id, nome, barCode, preco, ativo);
        produtoDto = new ProdutoDto(id, nome, barCode, preco, ativo);
    }

    @Test
    void findById()
    {
        Mockito.when(service.findById(id)).thenReturn(produto);

        ResponseEntity<ProdutoDto> response = resource.findById(id);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getIdProduto());
        assertEquals(nome, response.getBody().getNome());
        assertEquals(preco, response.getBody().getPreco());
        assertEquals(barCode, response.getBody().getBarCode());
        assertEquals(ativo, response.getBody().getAtivo());
    }

    @Test
    void create()
    {
        Mockito.when(service.create(Mockito.any())).thenReturn(produto);

        ResponseEntity<ProdutoDto> response = resource.create(produtoDto);

        assertNotNull(response);
        assertNotNull(response.getHeaders().get("Location"));
        assertNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void findAll()
    {
        Mockito.when(service.findAll()).thenReturn(List.of(produto));

        ResponseEntity<List<ProdutoDto>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ProdutoDto.class, response.getBody().get(0).getClass());
        assertEquals(id, response.getBody().get(0).getIdProduto());
        assertEquals(nome, response.getBody().get(0).getNome());
        assertEquals(preco, response.getBody().get(0).getPreco());
        assertEquals(barCode, response.getBody().get(0).getBarCode());
        assertEquals(ativo, response.getBody().get(0).getAtivo());
    }

    @Test
    void update()
    {
        Mockito.when(service.update(Mockito.any())).thenReturn(produto);

        ResponseEntity<ProdutoDto> response = resource.update(id, produtoDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(id, response.getBody().getIdProduto());
        assertEquals(nome, response.getBody().getNome());
        assertEquals(preco, response.getBody().getPreco());
        assertEquals(barCode, response.getBody().getBarCode());
        assertEquals(ativo, response.getBody().getAtivo());
    }

    @Test
    void findByBarCode()
    {
        Mockito.when(service.findByBarCode(Mockito.any())).thenReturn(produto);

        ResponseEntity<ProdutoDto> response = resource.findByBarCode(barCode);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(id,  response.getBody().getIdProduto());
        assertEquals(nome, response.getBody().getNome());
        assertEquals(preco, response.getBody().getPreco());
        assertEquals(barCode, response.getBody().getBarCode());
        assertEquals(ativo, response.getBody().getAtivo());
    }

    @Test
    void delete()
    {
        Mockito.doNothing().when(service).delete(id);

        ResponseEntity<ProdutoDto> response = resource.delete(id);

        assertNotNull(response);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        Mockito.verify(service, Mockito.times(1)).delete(id);
    }

    @Test
    void softDelete()
    {
        Mockito.when(service.softDelete(Mockito.any())).thenReturn(produto);

        ResponseEntity<ProdutoDto> response = resource.softDelete(id,  produtoDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(id, response.getBody().getIdProduto());
        assertEquals(nome, response.getBody().getNome());
        assertEquals(preco, response.getBody().getPreco());
        assertEquals(barCode, response.getBody().getBarCode());
        assertEquals(ativo, response.getBody().getAtivo());
    }
}