package cepein.cepein_atividade2.services.impl;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.repositories.AtendenteRepository;
import cepein.cepein_atividade2.services.exceptions.DataIntegrityException;
import cepein.cepein_atividade2.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AtendenteServiceImplTest
{
    public static final String notFoundMessage = "Atendente não encontrado!";
    public static final String dataIntegrityMessage = "Cpf já existe no sistema!";
    @InjectMocks
    AtendenteServiceImpl service;

    @Mock
    AtendenteRepository repository;

    private Optional<Atendente> atendenteOptional;
    private AtendenteDto atendenteDto;
    private Atendente atendente;

    private final static Integer id = 1;
    private final static String nome = "Pedro";
    private final static String cpf = "11111111111";
    private final static Boolean ativo = true;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        startAtendentes();
    }

    private void startAtendentes()
    {
        atendente = new Atendente(id, nome, cpf, ativo);
        atendenteDto = new AtendenteDto(id, nome, cpf, ativo);
        atendenteOptional = Optional.of(new Atendente(id, nome, cpf, ativo));
    }

    @Test
    void whenFindByIdReturnAtendente()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(atendenteOptional);

        Atendente response = service.findById(id);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
        assertEquals(ativo, response.getAtivo());
    }

    @Test
    void whenFindByIdThenThrowObjectNotFoundException()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findById(id);
        } catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(notFoundMessage, ex.getMessage());
        }
    }

    @Test
    void whenCreateAtendenteReturnAtendente()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(atendente);

        Atendente response = service.create(atendenteDto);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
    }

    @Test
    void whenCreateThenThrowDataIntegrityException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.create(atendenteDto);
        } catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(DataIntegrityException.class, ex.getClass());
            assertEquals(dataIntegrityMessage, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnListOfAtendentes()
    {
        Mockito.when(repository.findAll()).thenReturn(List.of(atendente));

        List<Atendente> response = service.findAll();

        assertNotNull(response);
        assertEquals(Atendente.class, response.get(0).getClass());
        assertEquals(id, response.get(0).getIdAtendente());
        assertEquals(nome, response.get(0).getNome());
        assertEquals(cpf, response.get(0).getCpf());
        assertEquals(1, response.size());
    }

    @Test
    void whenUpdateAtendenteReturnAtendente()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(atendente);

        Atendente response = service.update(atendenteDto);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
    }

    @Test
    void whenUpdateThenThrowDataIntegrityException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.update(atendenteDto);
        } catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(DataIntegrityException.class, ex.getClass());
            assertEquals(dataIntegrityMessage, ex.getMessage());
        }
    }

    @Test
    void whenFindByCpfThenReturnOptionalAtendente()
    {
        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(atendenteOptional);

        Atendente response = service.findByCpf(atendenteDto);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
    }

    @Test
    void whenFindByCpfThenObjectNotFoundException()
    {
        Mockito.when(repository.findByCpf(Mockito.any())).thenThrow(new ObjectNotFoundException(notFoundMessage));

        try
        {
            service.findByCpf(atendenteDto);
        } catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(notFoundMessage, ex.getMessage());
        }
    }

    @Test
    void whenValidationByCpfThenThrowDataIntegrityException()
    {
        Mockito.when(repository.findByCpf(Mockito.any())).thenThrow(new DataIntegrityException(dataIntegrityMessage));

        try
        {
            service.validationByCpf(atendenteDto);
        } catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(DataIntegrityException.class, ex.getClass());
            assertEquals(dataIntegrityMessage, ex.getMessage());
        }
    }

    @Test
    void whenDeleteThenSuccess()
    {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(atendenteOptional);
        Mockito.doNothing().when(repository).delete(Mockito.any());
        service.delete(atendenteDto);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void whenDeleteThenThrowObjectNotFoundException()
    {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(new ObjectNotFoundException(notFoundMessage));
        try
        {
            service.delete(atendenteDto);
        } catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(notFoundMessage, ex.getMessage());
        }
    }

    @Test
    void whenSoftDeleteThenUpdateSuccess()
    {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(atendenteOptional);

        Atendente response = service.softDelete(atendenteDto);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
        assertEquals(false, response.getAtivo());
    }

    @Test
    void whenSoftDeleteThenSoftDelete()
    {
        Mockito.when(repository.findById(Mockito.any())).thenThrow(new ObjectNotFoundException(notFoundMessage));
        try
        {
            Atendente response = service.softDelete(atendenteDto);
        }
        catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(notFoundMessage, ex.getMessage());
        }
    }
}