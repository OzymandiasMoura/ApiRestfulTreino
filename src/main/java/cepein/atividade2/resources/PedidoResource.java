package cepein.atividade2.resources;

import cepein.atividade2.domain.Atendente;
import cepein.atividade2.domain.Pedido;
import cepein.atividade2.domain.Produto;
import cepein.atividade2.domain.dto.AtendenteDto;
import cepein.atividade2.domain.dto.PedidoDto;
import cepein.atividade2.domain.dto.ProdutoDto;
import cepein.atividade2.domain.mapper.AtendenteMapper;
import cepein.atividade2.domain.mapper.PedidoMapper;
import cepein.atividade2.domain.mapper.ProdutoMapper;
import cepein.atividade2.resources.tool.DataMapper;
import cepein.atividade2.services.AtendenteService;
import cepein.atividade2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource
{
    PedidoMapper mapper = new PedidoMapper();

    @Autowired
    private PedidoService service;

    @Autowired
    private AtendenteService atendenteService;

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
        Pedido pedido = service.create(pedidoDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> update(@PathVariable Integer id, @RequestBody PedidoDto pedidoDto)
    {
        Pedido pedido = service.update(pedidoDto);
        return ResponseEntity.ok().body(PedidoMapper.entityToDto(pedido));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/closeorder/{id}")
    public ResponseEntity<PedidoDto> closeOrder(@PathVariable Integer id)
    {
        Pedido pedido = service.closeOrder(id);

        return ResponseEntity.ok().body(PedidoMapper.entityToDto(pedido));
    }

    @GetMapping(value = "/{id}/produtos")
    public ResponseEntity<List<ProdutoDto>> findAllProdutosById(@PathVariable Integer id)
    {
        List<Produto> list = service.findProdutosInPedido(id);
        List<ProdutoDto> listDto = list.stream().map(ProdutoMapper::entityToDto).toList();

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{inicio}/{fim}")
    public ResponseEntity<List<PedidoDto>> findPedidosBetweenDates(@PathVariable String inicio, @PathVariable String fim)
    {
        LocalDate dateinicio = DataMapper.stringToDate(inicio);
        LocalDate dateFim = DataMapper.stringToDate(fim);

        List<Pedido> list = service.findPedidosBetweenDates(dateinicio, dateFim);
        List<PedidoDto> listDto = list.stream().map(PedidoMapper::entityToDto).toList();

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/atendente/{id}")
    public ResponseEntity<List<PedidoDto>> findByAtendenteOrderByDataAberturaPedido(@PathVariable Integer id)
    {
        Atendente atendente = atendenteService.findById(id);
        List<Pedido> pedidos = service.findByAtendenteOrderByDataAberturaPedido(AtendenteMapper.entityToDto(atendente));

        List<PedidoDto> listDto = pedidos.stream().map(PedidoMapper::entityToDto).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/atendentedesc/{id}")
    public ResponseEntity<List<PedidoDto>> findByAtendenteOrderByDataAberturaPedidoDesc(@PathVariable Integer id)
    {
        AtendenteDto dto = AtendenteMapper.entityToDto(atendenteService.findById(id));
        List<Pedido> pedidos = service.findByAtendenteOrderByDataAberturaPedidoDesc(dto);

        List<PedidoDto> listDto = pedidos.stream().map(PedidoMapper::entityToDto).toList();
        return ResponseEntity.ok().body(listDto);
    }
}
