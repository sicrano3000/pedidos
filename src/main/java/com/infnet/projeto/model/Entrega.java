package com.infnet.projeto.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ENTREGA")
public class Entrega implements Serializable {
	
	private static final long serialVersionUID = -8928317227075818606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String endereco;
	private Integer previsaoEntrega;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getPrevisaoEntrega() {
		return 60;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", endereco=" + endereco + ", previsaoEntrega=" + previsaoEntrega + ", cliente="
				+ cliente + "]";
	}

}
