package com.infnet.projeto.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.dto.MassaDTO;
import com.infnet.projeto.enums.Borda;
import com.infnet.projeto.enums.Sabor;
import com.infnet.projeto.enums.Tipo;

@Entity
@Table(name = "TB_MASSA")
public class Massa implements Serializable {
	
	private static final long serialVersionUID = -278551675699138530L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true)
	private String descricao;
	
	private Double valor;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sabor sabor;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Borda borda;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToOne
	private Pedido pedido;
	
	public static Massa create(MassaDTO massaDTO) {
		return new ModelMapper().map(massaDTO, Massa.class);
	} 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		switch (this.sabor) {
		case MUSSARELA:
			valor = this.tipo == Tipo.PIZZA ? 50.0 : 25.0;
			break;
		case PRESUNTO:
			valor = this.tipo == Tipo.PIZZA ? 50.0 : 25.0;
			break;
		case QUEIJO4:
			valor = this.tipo == Tipo.PIZZA ? 60.0 : 30.0;
			break;
		case BACON:
			valor = this.tipo == Tipo.PIZZA ? 60.0 : 30.0;
			break;

		default:
			valor = 0.0;
			break;
		}
		
		return this.borda != Borda.NORMAL ? valor + 10.0 : valor;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	public Borda getBorda() {
		return borda;
	}
	public void setBorda(Borda borda) {
		this.borda = borda;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}	
	public Pedido getEntrega() {
		return pedido;
	}
	public void setEntrega(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, descricao, sabor, valor, borda, tipo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Massa other = (Massa) obj;
		return Objects.equals(id, other.id) && Objects.equals(descricao, other.descricao) && Objects.equals(sabor, other.sabor) 
				&& Objects.equals(valor, other.valor) && Objects.equals(borda, other.borda) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Massa [id=" +id + "descricao=" + descricao + ", valor=" + valor + ", sabor=" + sabor + ", borda=" + borda + ", tipo=" + tipo + "]";
	}

}
