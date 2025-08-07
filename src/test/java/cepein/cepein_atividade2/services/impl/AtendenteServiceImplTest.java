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
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtendenteServiceImplTest
{
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

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        startAtendentes();
    }

    private void startAtendentes()
    {
        atendente = new Atendente(id, nome, cpf);
        atendenteDto = new AtendenteDto(id, nome, cpf);
        atendenteOptional = Optional.of(new Atendente(id, nome, cpf));
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
    }

    @Test
    void whenFindByIdThenThrowObjectNotFoundException()
    {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Atendente não encontrado!"));

        try
        {
            service.findById(id);
        }
        catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Atendente não encontrado!", ex.getMessage());
        }
    }

    @Test
    void whenCreateAtendenteReturnAtendente()
    {
        Mockito.when(repository.save(Mockito.any())).thenReturn(atendente);

        Atendente response =service.create(atendenteDto);

        assertNotNull(response);
        assertEquals(Atendente.class, response.getClass());
        assertEquals(id, response.getIdAtendente());
        assertEquals(nome, response.getNome());
        assertEquals(cpf, response.getCpf());
    }

    @Test
    void whenCreateThenThrowDataIntegrityException()
    {
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException("Cpf já existe no sistema!"));

        try
        {
            service.create(atendenteDto);
        }
        catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(DataIntegrityException.class, ex.getClass());
            assertEquals("Cpf já existe no sistema!", ex.getMessage());
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
        Mockito.when(repository.save(Mockito.any())).thenThrow(new DataIntegrityException("Cpf já existe no sistema!"));

        try
        {
            service.update(atendenteDto);
        }
        catch (Exception ex)
        {
            assertNotNull(ex);
            assertEquals(DataIntegrityException.class, ex.getClass());
            assertEquals("Cpf já existe no sistema!", ex.getMessage());
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


}