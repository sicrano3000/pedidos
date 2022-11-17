package com.infnet.projeto.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.model.Pedido;

public class PedidoCompletoDTO implements Serializable {
	
	private static final long serialVersionUID = -6251543827725455825L;
	
	@NotBlank
	private String endereco;
	@NotNull
	private ClienteDTO cliente;
	@NotNull
	private String previsaoEntrega;
	private Double valorTotal;
	@NotNull
	private List<MassaDTO> carrinho;
	
	public static PedidoCompletoDTO create(Pedido pedido) {
		return new ModelMapper().map(pedido, PedidoCompletoDTO.class);
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public String getPrevisaoEntrega() {
		return previsaoEntrega;
	}
	public void setPrevisaoEntrega(String previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<MassaDTO> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(List<MassaDTO> carrinho) {
		this.carrinho = carrinho;
	}

}
