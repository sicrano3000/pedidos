package com.infnet.projeto.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infnet.projeto.dto.CarrinhoDTO;
import com.infnet.projeto.dto.PedidoCompletoDTO;
import com.infnet.projeto.dto.PedidoDTO;

public interface IPedidoService {

	PedidoCompletoDTO salvar(@Valid PedidoDTO pedidoDTO) throws Exception;

	Page<PedidoCompletoDTO> listarTodos(Pageable pageable);

	PedidoCompletoDTO listarPorId(Long id);

	PedidoDTO atualizar(Long id, PedidoDTO pedidoDTO);
	
	PedidoCompletoDTO adicionarAoCarrinho(Long id, CarrinhoDTO carrinhoDTO);

	void deletar(Long id);

}
