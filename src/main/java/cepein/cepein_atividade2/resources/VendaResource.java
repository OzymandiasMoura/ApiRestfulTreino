package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.Venda;
import cepein.cepein_atividade2.domain.dto.PedidoDto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.dto.VendaDto;
import cepein.cepein_atividade2.domain.ids.VendaId;
import cepein.cepein_atividade2.domain.mapper.PedidoMapper;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.services.PedidoService;
import cepein.cepein_atividade2.services.ProdutoService;
import cepein.cepein_atividade2.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

import java.net.URI;

@RestController
@RequestMapping(value = "/venda")
public class VendaResource
{
    @Autowired
    private VendaService service;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Venda> createVenda(@RequestBody VendaDto dto)
    {
        Venda venda = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getIdVenda()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Venda>> findAll()
    {
        List<Venda> vendas = service.findAll();
        return ResponseEntity.ok().body(vendas);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Venda> findById(@RequestParam int idProduto, @RequestParam int idPedido)
    {
        VendaId idVenda = new VendaId(idProduto, idPedido);
        Venda venda = service.findById(idVenda);

        return ResponseEntity.ok().body(venda);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Venda> update(@RequestBody  VendaDto dto)
    {
        Venda venda = service.update(dto);
        return ResponseEntity.ok().body(venda);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> delete(@RequestParam int idProduto, @RequestParam int idPedido)
    {
        VendaId idVenda = new VendaId(idProduto, idPedido);
        service.delete(idVenda);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/pedido")
    public ResponseEntity<List<Venda>> findByPedido(@RequestBody PedidoDto dto)
    {
        List<Venda> vendas = service.findByPedido(dto);
        return ResponseEntity.ok().body(vendas);
    }

    @GetMapping(value = "/find/produto")
    public ResponseEntity<List<Venda>> findByProduto(@RequestBody ProdutoDto produtoDto)
    {
        List<Venda> vendas = service.findByProduct(produtoDto);
        return ResponseEntity.ok().body(vendas);
    }

    @GetMapping(value = "/find/produtopedido")
    public ResponseEntity<Venda> findByProdutosAndPedido(@RequestParam int idProduto, @RequestParam int idPedido)
    {
        Venda vendas = service.findByProdutoAndPedido(ProdutoMapper.entityToDto(produtoService.findById(idProduto)), PedidoMapper.entityToDto(pedidoService.findById(idPedido)));
        return ResponseEntity.ok().body(vendas);
    }
}
