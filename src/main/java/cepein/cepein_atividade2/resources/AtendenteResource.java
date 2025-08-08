package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.services.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
