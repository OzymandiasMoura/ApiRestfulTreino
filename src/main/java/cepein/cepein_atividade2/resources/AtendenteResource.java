package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.services.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/atendente")
public class AtendenteResource
{
    @Autowired
    private AtendenteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AtendenteDto> findById(@PathVariable Integer id)
    {
        Atendente atendente = service.findById(id);
        return ResponseEntity.ok().body(new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo()));
    }

    @PostMapping
    public ResponseEntity<AtendenteDto> create(@RequestBody AtendenteDto atendenteDto)
    {
        Atendente atendente = service.create(atendenteDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atendente.getIdAtendente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<AtendenteDto>> findAll()
    {
        List<AtendenteDto> atendentes = new ArrayList<>();
        service.findAll().stream().map(atendente -> atendentes.add(new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo())));

        return ResponseEntity.ok().body(atendentes);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AtendenteDto> update(@PathVariable Integer id, @RequestBody AtendenteDto atendenteDto)
    {
        Atendente atendente = service.update(atendenteDto);
        return ResponseEntity.ok().body(new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo()));
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<AtendenteDto> findByCpf(@PathVariable String cpf)
    {
        Atendente atendente = service.findByCpf(cpf);
        return ResponseEntity.ok().body(new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AtendenteDto> delete(@PathVariable Integer id)
    {
        Atendente atendente = service.findById(id);
        AtendenteDto atendenteDto = new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo());
        service.delete(atendenteDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<AtendenteDto> softDelete(@PathVariable Integer id,  @RequestBody AtendenteDto atendenteDto)
    {
        Atendente atendente = service.softDelete(atendenteDto);
        return ResponseEntity.ok().body(new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo()));
    }
}
