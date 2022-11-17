package com.infnet.projeto.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infnet.projeto.dto.PedidoCompletoDTO;
import com.infnet.projeto.dto.PedidoDTO;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = -8928317227075818606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String endereco;
	
	private String previsaoEntrega;
	
	private Double valorTotal;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime dataCompra;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Massa> carrinho;
	
	public Pedido() {
		this.dataCompra = LocalDateTime.now();
	}
	
	public Pedido(Pedido pedido, List<Massa> massas) {
		this.endereco = pedido.getEndereco();
		this.cliente = pedido.getCliente();
		this.previsaoEntrega = this.getPrevisaoEntrega();
		this.valorTotal = pedido.getValorTotal();
		this.carrinho = massas;
		this.dataCompra = LocalDateTime.now();
	}
	
	public static Pedido create(PedidoDTO pedidoDTO) {
		return new ModelMapper().map(pedidoDTO, Pedido.class);
	} 
	
	public static Pedido create(PedidoCompletoDTO pedidoCompletoDTO) {
		return new ModelMapper().map(pedidoCompletoDTO, Pedido.class);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDataCompra() {
		return LocalDateTime.now();
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getPrevisaoEntrega() {
		return "60 minutos";
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	public List<Massa> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(List<Massa> carrinho) {
		this.carrinho = carrinho;
	}
	
	@Override
	public String toString() {
		return "Entrega [id=" + id + ", endereco=" + endereco + ", previsaoEntrega=" + previsaoEntrega + ", dataCompra="
				+ dataCompra + ", cliente=" + cliente + "]";
	}

}
