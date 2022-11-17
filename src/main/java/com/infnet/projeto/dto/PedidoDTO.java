package com.infnet.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.model.Pedido;

public class PedidoDTO implements Serializable {
	
	private static final long serialVersionUID = -6950543827725455825L;
	
	@NotBlank
	private String endereco;
	@NotNull
	private String cpf;
	
	public static PedidoDTO create(Pedido pedido) {
		return new ModelMapper().map(pedido, PedidoDTO.class);
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
