package com.infnet.projeto.service;

import java.util.List;

import com.infnet.projeto.dto.ClienteDTO;
import com.infnet.projeto.model.Cliente;

public interface IClienteService {
	
	Cliente salvar(ClienteDTO clienteDTO) throws Exception;
	
	List<Cliente> listarTodos();
	
	Cliente listarPorId(Long id) throws Exception;

	Cliente atualizar(Long id, ClienteDTO clienteDTO);

	void deletar(Long id) throws Exception;
	
}
