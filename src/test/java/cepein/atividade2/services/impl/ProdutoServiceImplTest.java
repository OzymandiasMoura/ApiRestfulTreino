package cepein.atividade2.services.impl;

import cepein.atividade2.domain.Produto;
import cepein.atividade2.domain.dto.ProdutoDto;
import cepein.atividade2.repositories.ProdutoRepository;
import cepein.atividade2.services.exceptions.DataIntegrityException;
import cepein.atividade2.services.exceptions.InvalidFormatException;
import cepein.atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceImplTest
{
    private Optional<Produto> produtoOptional;
    private ProdutoDto produtoDto;
    private Produto produto;

    private final static Integer id = 1;
    private final static String nome = "Geladeira";
    private final static String barCode = "2222222222222";
    private final static Double preco = 1550.50;
    private final static Boolean ativo = true;

    public static final String notFoundMessage = "Produto não encontrado!";
    public static final String dataIntegrityMessage = "Código de barras já existe no sistema!";
    public static final String invalidFormatMessage = "Formato do código de barras é invalido.";

    @InjectMocks
    ProdutoServiceImpl service;

    @Mock
    ProdutoRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        produto = new Produto(id, nome, barCode, preco, ativo);
        produtoOptional = Optional.of(new Produto(id, nome, barCode, preco, ativo));
        produtoDto = new ProdutoDto(id, nome, barCode, preco, ativo);
    }

    @Test
    void whenFindByIdThenSuccess()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(produtoOptional);

        Produto response = service.findById(id);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenFindByIdThenException()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findById(id);
        }
        catch (Exception e)
        {
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertNotNull(e);
        }
    }

    @Test
    void whenCreateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(produto);

        Produto response = service.create(produtoDto);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenCreateThenException()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.create(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void whenCreateThenExceptionInvalidFormat()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new InvalidFormatException(invalidFormatMessage));

        try
        {
            service.create(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(invalidFormatMessage, e.getMessage());
            assertEquals(InvalidFormatException.class, e.getClass());
        }
    }

    @Test
    void whenFindAllThenSuccess()
    {
        Mockito.when(repository.findAll()).thenReturn(List.of(produto));
        List<Produto> response = service.findAll();

        assertNotNull(response);
        assertEquals(Produto.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdProduto());
        assertEquals(nome, response.get(0).getNome());
        assertEquals(barCode, response.get(0).getBarCode());
        assertEquals(preco, response.get(0).getPreco());
        assertEquals(ativo, response.get(0).getAtivo());
    }

    @Test
    void whenUpdateThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(produto);

        Produto response = service.update(produtoDto);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenUpdateThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));
        try
        {
            service.update(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void whenFindByBarCodeThenSuccess()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenReturn(produtoOptional);

        Produto response = service.findByBarCode(barCode);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenFindByBarCodeThenException()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findByBarCode(barCode);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenValidationByBarCodeThenException()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.validationByBarCode(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(dataIntegrityMessage, e.getMessage());
            assertEquals(DataIntegrityException.class, e.getClass());
        }
    }

    @Test
    void whenValidateBarCodeFormatException()
    {
        Mockito.when(repository.findByBarCode(Mockito.any())).thenThrow(new InvalidFormatException(invalidFormatMessage));

        try
        {
            service.validationByBarCode(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(invalidFormatMessage, e.getMessage());
            assertEquals(InvalidFormatException.class, e.getClass());
        }
    }

    @Test
    void whenDeleteThenSuccess()
    {
        Mockito.doNothing().when(repository).delete(Mockito.any());
        service.delete(id);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void whenDeleteThenException()
    {
        Mockito.doNothing().when(repository).delete(Mockito.any());

        try
        {
            service.delete(id);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(DataIntegrityException.class, e.getClass());
            assertEquals(dataIntegrityMessage, e.getMessage());
        }
    }

    @Test
    void whenSoftDeleteThenSuccess()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(produto);

        Produto response = service.softDelete(produtoDto);

        assertNotNull(response);
        assertEquals(Produto.class, response.getClass());
        assertEquals(id, response.getIdProduto());
        assertEquals(nome, response.getNome());
        assertEquals(barCode, response.getBarCode());
        assertEquals(preco, response.getPreco());
        assertEquals(false, response.getAtivo());
    }

    @Test
    void whenSoftDeleteThenException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.softDelete(produtoDto);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void findByPrecoLessThanSuccess()
    {
        Mockito.when(repository.findProdutosByPrecoLessThan(Mockito.anyDouble())).thenReturn(List.of(produto));

        List<Produto> response = service.findByPrecoLessThan(preco);

        assertNotNull(response);
        assertEquals(Produto.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdProduto());
        assertEquals(nome, response.get(0).getNome());
        assertEquals(barCode, response.get(0).getBarCode());
        assertEquals(preco, response.get(0).getPreco());
        assertEquals(ativo, response.get(0).getAtivo());
        assertEquals(1, response.size());

    }

    @Test
    void findByPrecoLessThanException()
    {
        Mockito.when(repository.findProdutosByPrecoLessThan(Mockito.anyDouble())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            List<Produto> response = service.findByPrecoLessThan(preco);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void findByPrecoLessThanEqualSuccess()
    {
        Mockito.when(repository.findProdutosByPrecoLessThanEqual(Mockito.anyDouble())).thenReturn(List.of(produto));

        List<Produto> response = service.findByPrecoLessThanEqual(preco);

        assertNotNull(response);
        assertEquals(Produto.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdProduto());
        assertEquals(nome, response.get(0).getNome());
        assertEquals(barCode, response.get(0).getBarCode());
        assertEquals(preco, response.get(0).getPreco());
        assertEquals(ativo, response.get(0).getAtivo());
        assertEquals(1, response.size());
    }

    @Test
    void findByPrecoLessThanEqualException()
    {
        Mockito.when(repository.findProdutosByPrecoLessThanEqual(Mockito.anyDouble())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            List<Produto> response = service.findByPrecoLessThanEqual(preco);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void findByPrecoGreaterThanSuccess()
    {
        Mockito.when(repository.findProdutosByPrecoGreaterThan(Mockito.anyDouble())).thenReturn(List.of(produto));

        List<Produto> response = service.findByPrecoGreaterThan(preco);

        assertNotNull(response);
        assertEquals(Produto.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdProduto());
        assertEquals(nome, response.get(0).getNome());
        assertEquals(barCode, response.get(0).getBarCode());
        assertEquals(preco, response.get(0).getPreco());
        assertEquals(ativo, response.get(0).getAtivo());
        assertEquals(1, response.size());
    }

    @Test
    void findByPrecoGreaterThanException()
    {
        Mockito.when(repository.findProdutosByPrecoGreaterThan(Mockito.anyDouble())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            List<Produto> response = service.findByPrecoGreaterThan(preco);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void findByPrecoGreaterThanEqualSuccess()
    {
        Mockito.when(repository.findProdutosByPrecoGreaterThanEqual(Mockito.anyDouble())).thenReturn(List.of(produto));

        List<Produto> response = service.findByPrecoGreaterThanEqual(preco);

        assertNotNull(response);
        assertEquals(Produto.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdProduto());
        assertEquals(nome, response.get(0).getNome());
        assertEquals(barCode, response.get(0).getBarCode());
        assertEquals(preco, response.get(0).getPreco());
        assertEquals(ativo, response.get(0).getAtivo());
        assertEquals(1, response.size());
    }

    @Test
    void findByPrecoGreaterThanEqualException()
    {
        Mockito.when(repository.findProdutosByPrecoGreaterThanEqual(Mockito.anyDouble())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            List<Produto> response = service.findByPrecoGreaterThanEqual(preco);
        }
        catch (Exception e)
        {
            assertNotNull(e);
            assertEquals(notFoundMessage, e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    void findPedidosInProdutos()
    {
    }
}