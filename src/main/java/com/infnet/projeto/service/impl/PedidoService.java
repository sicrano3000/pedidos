package com.infnet.projeto.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infnet.projeto.dto.CarrinhoDTO;
import com.infnet.projeto.dto.MassaDTO;
import com.infnet.projeto.dto.PedidoCompletoDTO;
import com.infnet.projeto.dto.PedidoDTO;
import com.infnet.projeto.exception.ResourceNotFoundException;
import com.infnet.projeto.model.Massa;
import com.infnet.projeto.model.Pedido;
import com.infnet.projeto.repository.ClienteRepository;
import com.infnet.projeto.repository.MassaRepository;
import com.infnet.projeto.repository.PedidoRepository;
import com.infnet.projeto.service.IPedidoService;

@Service
public class PedidoService implements IPedidoService {
	
	final PedidoRepository pedidoRepository;
	final MassaRepository massaRepository;
	final ClienteRepository clienteRepository;

	public PedidoService(PedidoRepository pedidoRepository, MassaRepository massaRepository, ClienteRepository clienteRepository) {
		this.pedidoRepository = pedidoRepository;
		this.massaRepository = massaRepository;
		this.clienteRepository = clienteRepository;
	}

	@Override
	public PedidoCompletoDTO salvar(PedidoDTO pedidoDTO) throws Exception {
		var cliente = clienteRepository.findByCpf(pedidoDTO.getCpf()).orElseThrow(() -> new ResourceNotFoundException("O Cliente informado não existe!"));
		
		if (pedidoRepository.findByCliente(cliente).isPresent()) {
			throw new Exception("Já exite um pedido para esse Cliente!");
		}
		
		var pedido = Pedido.create(pedidoDTO);
		
		pedido.setCliente(cliente);
		
		return PedidoCompletoDTO.create(pedidoRepository.save(pedido));
	}

	@Override
	public Page<PedidoCompletoDTO> listarTodos(Pageable pageable) {	
		var page = pedidoRepository.findAll(pageable);
		
		page.getContent().stream().forEach(c-> {
			double valorTotal = calcularValorTota(c.getCarrinho());
			c.setValorTotal(valorTotal);
		});
		
		return page.map(this::convertToPedidoDTO);
	}
	
	private PedidoCompletoDTO convertToPedidoDTO(Pedido pedido) {
		return PedidoCompletoDTO.create(pedido);
	}
	
	private Massa convertToMassa(MassaDTO massaDTO) {
		return Massa.create(massaDTO);
	}

	@Override
	public PedidoCompletoDTO listarPorId(Long id) {
		var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido com id = " + id + " não encontrado!"));
		
		pedido.setValorTotal(calcularValorTota(pedido.getCarrinho()));
		
		return PedidoCompletoDTO.create(pedido);
	}

	@Override
	public PedidoDTO atualizar(Long id, PedidoDTO pedidoDTO) {
		var pedido = pedidoRepository.findById(id).get();
		
		pedido.setEndereco(pedidoDTO.getEndereco());
		
		return PedidoDTO.create(pedidoRepository.save(pedido));
	}

	@Override
	public void deletar(Long id) {
		var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido com id = " + id + " não encontrado!"));
		
		pedidoRepository.delete(pedido);
	}

	public PedidoCompletoDTO adicionarAoCarrinho(Long id, CarrinhoDTO carrinhoDTO) {
		var pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido com id = " + id + " não encontrado!"));
		
		var massas = carrinhoDTO.getCarrinho()
				.stream()
				.map(this::convertToMassa)				
				.collect(Collectors.toList());
		
		massas.forEach(m -> m.setEntrega(pedido));
		
		var massasSalvas = massaRepository.saveAll(massas);
		var novoPedido = new Pedido(pedido, massasSalvas);
		
		novoPedido.setValorTotal(calcularValorTota(massasSalvas));
		
		pedidoRepository.save(novoPedido);
		
		return PedidoCompletoDTO.create(novoPedido);
	}
	
	private Double calcularValorTota(List<Massa> massas) {
		return massas.stream()
			    .mapToDouble(v -> v.getValor())
			    .sum();
	}

}