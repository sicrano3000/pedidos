package com.infnet.projeto.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.infnet.projeto.dto.ClienteDTO;
import com.infnet.projeto.exception.ResourceNotFoundException;
import com.infnet.projeto.model.Cliente;
import com.infnet.projeto.repository.ClienteRepository;
import com.infnet.projeto.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public Cliente salvar(ClienteDTO clienteDTO) throws Exception {
		var cliente = new Cliente();
		
		if (clienteRepository.findByCpf(clienteDTO.getCpf()).isPresent()) {
			throw new Exception("Já existe um Cliente com este CPF!");
		}
		
		BeanUtils.copyProperties(clienteDTO, cliente);
		
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente listarPorId(Long id) throws Exception {
		return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com id = " + id + " não encontrado!"));
	}

	@Override
	public Cliente atualizar(Long id, ClienteDTO clienteDTO) {
		var cliente = clienteRepository.findById(id).get();
		
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setNome(clienteDTO.getNome());
		
		return clienteRepository.save(cliente);
	}

	@Override
	public void deletar(Long id) throws Exception {		
		var cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com id = " + id + " não encontrado!"));
		
		clienteRepository.delete(cliente);
	}

}
