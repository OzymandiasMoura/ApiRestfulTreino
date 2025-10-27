package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Mesa;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.MesaDto;
import cepein.cepein_atividade2.domain.mapper.AtendenteMapper;
import cepein.cepein_atividade2.domain.mapper.MesaMapper;
import cepein.cepein_atividade2.services.AtendenteService;
import cepein.cepein_atividade2.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/mesa")
public class MesaResource
{
    @Autowired
    private MesaService service;

    @Autowired
    private AtendenteService atendenteService;

    @PostMapping
    public ResponseEntity<MesaDto> createMesa(@RequestBody MesaDto mesaDto)
    {
        Mesa mesa = service.createMesa(mesaDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mesa.getNumMesa()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<MesaDto>> getAllMesas()
    {
        List<Mesa> mesas = service.findAll();

        return ResponseEntity.ok().body(mesas.stream().map(MesaMapper::entityToDto).toList());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<MesaDto> findById(@PathVariable Integer id)
    {
        Mesa mesa = service.findById(id);

        return ResponseEntity.ok().body(MesaMapper.entityToDto(mesa));
    }

    @GetMapping(value = "/find/atendente/{id}")
    public ResponseEntity<MesaDto> findMesaByAtendente(@PathVariable Integer id)
    {
        Atendente atendente = atendenteService.findById(id);

        Mesa mesa = service.findByAtendente(AtendenteMapper.entityToDto(atendente));

        return ResponseEntity.ok().body(MesaMapper.entityToDto(mesa));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<MesaDto> update(@PathVariable Integer id, @RequestBody MesaDto mesaDto)
    {
        Mesa mesa = service.updateMesa(id, mesaDto);

        return ResponseEntity.ok().body(MesaMapper.entityToDto(mesa));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<MesaDto> deleteMesa(@PathVariable Integer id)
    {
        service.deleteMesa(id);

        return ResponseEntity.noContent().build();
    }
}
