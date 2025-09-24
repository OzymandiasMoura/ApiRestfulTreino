package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Atendente;
import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.AtendenteDto;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.mapper.AtendenteMapper;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.resources.tool.DataMapper;
import cepein.cepein_atividade2.services.AtendenteService;
import cepein.cepein_atividade2.services.PedidoService;
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

        Pedido pedido = mapper.dtoToEntity(pedidoDto);

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

    @PutMapping(value = "/closeOrder/{id}")
    public ResponseEntity<PedidoDto> closeOrder(@PathVariable Integer id, @RequestBody PedidoDto pedidoDto)
    {
        Pedido pedido = PedidoMapper.dtoToEntity(pedidoDto);
        pedido.fecharPedido();

        PedidoDto dto = PedidoMapper.entityToDto(pedido);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}/produtos")
    public ResponseEntity<List<ProdutoDto>> findAllProdutosById(@PathVariable Integer id)
    {
        Pedido pedido = service.findById(id);
        List<Produto> list = service.findProdutosInPedido(PedidoMapper.entityToDto(pedido));
        List<ProdutoDto> listDto = list.stream().map(ProdutoMapper::entityToDto).toList();

        return ResponseEntity.ok().body(listDto);
    }

//    @GetMapping(value = "/ultimo")
//    public ResponseEntity<PedidoDto> findLastPedido()
//    {
//        Pedido pedido = service.findLastPedido();
//        return ResponseEntity.ok().body(PedidoMapper.entityToDto(pedido));
//    }

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
        AtendenteDto dto = new AtendenteDto(atendente.getIdAtendente(), atendente.getNome(), atendente.getCpf(), atendente.getAtivo());
        List<Pedido> pedidos = service.findByAtendenteOrderByDataAberturaPedido(dto);

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
