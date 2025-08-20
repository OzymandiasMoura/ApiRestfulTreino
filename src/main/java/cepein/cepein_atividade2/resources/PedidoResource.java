package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource
{
    PedidoMapper mapper = new PedidoMapper();

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> findById(@PathVariable Integer id)
    {
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(PedidoMapper.entityToDto(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll()
    {
        List<Pedido> pedidos = service.findAll();
        return ResponseEntity.ok().body(pedidos.stream().map(PedidoMapper::entityToDto).toList());
    }

    @PostMapping
    public ResponseEntity<PedidoDto> create(@RequestBody PedidoDto pedidoDto)
    {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(pedidoDto).getIdPedido()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> update(@PathVariable Integer id, @RequestBody PedidoDto pedidoDto)
    {
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(PedidoMapper.entityToDto(pedido));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/closeOrder/{id}")
    public ResponseEntity<PedidoDto> closeOrder(@PathVariable Integer id, @RequestBody PedidoDto pedidoDto)
    {
        Pedido pedido = service.closeOrder(pedidoDto);
        return ResponseEntity.ok().body(PedidoMapper.entityToDto(pedido));
    }
}
