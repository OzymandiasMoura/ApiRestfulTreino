package cepein.cepein_atividade2.resources;

import cepein.cepein_atividade2.domain.Pedido;
import cepein.cepein_atividade2.domain.Produto;
import cepein.cepein_atividade2.domain.dto.ProdutoDto;
import cepein.cepein_atividade2.domain.mapper.ProdutoMapper;
import cepein.cepein_atividade2.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource
{
    ProdutoMapper produtoMapper=  new ProdutoMapper();

    @Autowired
    ProdutoService produtoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Integer id)
    {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(ProdutoMapper.entityToDto(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> create(@RequestBody ProdutoDto produtoDto)
    {
        Produto produto = produtoService.create(produtoDto);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getIdProduto()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll()
    {
        return ResponseEntity.ok().body(produtoService.findAll().stream().map(ProdutoMapper::entityToDto).toList());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Integer id, @RequestBody ProdutoDto produtoDto)
    {
        Produto produto = produtoService.update(produtoDto);
        return ResponseEntity.ok().body(ProdutoMapper.entityToDto(produto));
    }

    @GetMapping(value = "/{barCode}")
    public ResponseEntity<ProdutoDto> findByBarCode(@PathVariable String barCode)
    {
        Produto produto = produtoService.findByBarCode(barCode);
        return ResponseEntity.ok().body(ProdutoMapper.entityToDto(produto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> delete(@PathVariable Integer id)
    {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<ProdutoDto> softDelete(@PathVariable Integer id, @RequestBody ProdutoDto produtoDto)
    {
        Produto produto = produtoService.softDelete(produtoDto);
        return ResponseEntity.ok().body(ProdutoMapper.entityToDto(produto));
    }

    @GetMapping(value = "/produtos/{id}")
    public ResponseEntity<List<Pedido>> findPedidosInProdutos(@PathVariable Integer id)
    {
        Produto produto = produtoService.findById(id);

        List<Pedido> pedidos = produtoService.findPedidosInProdutos(ProdutoMapper.entityToDto(produto));

        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping(value = "/valor/less/{preco}")
    public ResponseEntity<List<Produto>> findByPrecoLessThan(@PathVariable Double preco)
    {
        List<Produto> produtos = produtoService.findByPrecoLessThan(preco);

        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(value = "/valor/lessequal/{valor}")
    public ResponseEntity<List<Produto>> findByPrecoLessThanEqual(Double preco)
    {
        List<Produto> produtos = produtoService.findByPrecoLessThanEqual(preco);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(value = "/valor/greater/{valor}")
    public ResponseEntity<List<Produto>> findByPrecoGreaterThan(Double preco)
    {
        List<Produto> produtos = produtoService.findByPrecoGreaterThan(preco);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(value = "/valor/greaterequal/{valor}")
    public ResponseEntity<List<Produto>> findByPrecoGreaterThanEqual(Double preco)
    {
        List<Produto> produtos = produtoService.findByPrecoGreaterThanEqual(preco);
        return ResponseEntity.ok().body(produtos);
    }
}
