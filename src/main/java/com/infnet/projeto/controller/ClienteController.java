package com.infnet.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.projeto.dto.ClienteDTO;
import com.infnet.projeto.model.Cliente;
import com.infnet.projeto.service.impl.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	final ClienteService clienteService;

	@GetMapping
	@ApiOperation(value = "Retorna a lista de Clientes.", notes = "Get all Clientes.")
	public ResponseEntity<List<Cliente>> listarTodos() {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodos());		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um Cliente pelo id.", notes = "Get Cliente.")
	public ResponseEntity<Object> listarPorId(@PathVariable(value = "id") Long id) throws Exception {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarPorId(id));		
	}
	
	@PostMapping
	@ApiOperation(value = "Cria um Cliente.", notes = "Create Cliente.")
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ClienteDTO clienteDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(clienteDTO));
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "Altera um Cliente.", notes = "Update Cliente.")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id, 
											 @RequestBody @Valid ClienteDTO clienteDTO) {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(id, clienteDTO));		
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "Deleta um Cliente.", notes = "Delete Cliente.")
	public ResponseEntity<Object> excluir(@PathVariable(value = "id") Long id) throws Exception {
		clienteService.deletar(id);
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");		
	}
}
