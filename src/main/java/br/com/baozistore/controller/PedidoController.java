package br.com.baozistore.controller;

import br.com.baozistore.model.Pedido;
import br.com.baozistore.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.salvar(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id).map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (pedidoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}