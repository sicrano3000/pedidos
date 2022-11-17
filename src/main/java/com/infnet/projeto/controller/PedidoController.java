package com.infnet.projeto.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.projeto.dto.CarrinhoDTO;
import com.infnet.projeto.dto.PedidoDTO;
import com.infnet.projeto.service.impl.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de Pedidos.", notes = "Get all Pedidos.")
	public ResponseEntity<Page<?>> listarTodos(@RequestParam(value = "page", defaultValue = "0") int page,
											   @RequestParam(value = "limit", defaultValue = "12") int limit,
											   @RequestParam(value = "direction", defaultValue = "asc") String direction) {	
		var sorDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "dataCompra"));
		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listarTodos(pageable));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um Pedido pelo id.", notes = "Get Pedido.")
	public ResponseEntity<?> listarPorId(@PathVariable(value = "id") Long id) throws Exception {		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listarPorId(id));		
	}
	
	@PostMapping
	@ApiOperation(value = "Cria um Pedido.", notes = "Create Pedido.")
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PedidoDTO pedidoDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.salvar(pedidoDTO));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Altera um Pedido.", notes = "Update Pedido.")
	public ResponseEntity<?> atualizar(@PathVariable(value = "id") Long id, 
									   @RequestBody @Valid PedidoDTO pedidoDTO) {		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.atualizar(id, pedidoDTO));		
	}
	
	@PutMapping("/carrinho/{id}")
	@ApiOperation(value = "Adiciona um Pedido ao carrinho.", notes = "Update Pedido.")
	public ResponseEntity<?> adicionarAoCarrinho(@PathVariable(value = "id") Long id, 
									   		     @RequestBody @Valid CarrinhoDTO carrinhoDTO) {		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.adicionarAoCarrinho(id, carrinhoDTO));		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um Pedido.", notes = "Delete Pedido.")
	public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) throws Exception {
		pedidoService.deletar(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");		
	}

}
