package cepein.atividade2.resources;

import cepein.atividade2.domain.Atendente;
import cepein.atividade2.domain.dto.AtendenteDto;
import cepein.atividade2.domain.mapper.AtendenteMapper;
import cepein.atividade2.services.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/atendente")
public class AtendenteResource
{
    @Autowired
    private AtendenteService service;

    AtendenteMapper mapper = new AtendenteMapper();

    @GetMapping(value = "/{id}")
    public ResponseEntity<AtendenteDto> findById(@PathVariable Integer id)
    {
        Atendente atendente = service.findById(id);
        return ResponseEntity.ok().body(mapper.entityToDto(atendente));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<AtendenteDto> findByCpf(@PathVariable String cpf)
    {
        Atendente atendente = service.findByCpf(cpf);
        return ResponseEntity.ok().body(mapper.entityToDto(atendente));
    }

    @GetMapping
    public ResponseEntity<List<AtendenteDto>> findAll()
    {
        return ResponseEntity.ok().body(service.findAll().stream().map(AtendenteMapper::entityToDto).toList());
    }

    @PostMapping
    public ResponseEntity<AtendenteDto> create(@RequestBody AtendenteDto atendenteDto)
    {
        Atendente atendente = service.create(atendenteDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atendente.getIdAtendente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<AtendenteDto> update(@PathVariable Integer id, @RequestBody AtendenteDto atendenteDto)
    {
        Atendente atendente = service.update(id, atendenteDto);
        return ResponseEntity.ok().body(mapper.entityToDto(atendente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AtendenteDto> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<AtendenteDto> softDelete(@PathVariable Integer id)
    {
        Atendente atendente = service.softDelete(id);
        return ResponseEntity.ok().body(mapper.entityToDto(atendente));
    }

    @GetMapping(value = "/find/cpforid")
    public ResponseEntity<List<AtendenteDto>> findByCpfOrIdAtendente(@RequestParam String cpf, @RequestParam Integer idAtendente)
    {
        List<Atendente> atendente = service.findByCpfOrIdAtendente(cpf, idAtendente);

        return ResponseEntity.ok().body(atendente.stream().map(AtendenteMapper::entityToDto).toList());
    }
}
