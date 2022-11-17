package com.infnet.projeto.dto;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.model.Pedido;

public class CarrinhoDTO implements Serializable {
	
	private static final long serialVersionUID = -6951543827725455825L;
	
	private List<MassaDTO> carrinho;
	
	public static CarrinhoDTO create(Pedido pedido) {
		return new ModelMapper().map(pedido, CarrinhoDTO.class);
	}
	
	public List<MassaDTO> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(List<MassaDTO> carrinho) {
		this.carrinho = carrinho;
	}

}
