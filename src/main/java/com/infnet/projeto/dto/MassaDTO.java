package com.infnet.projeto.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.enums.Borda;
import com.infnet.projeto.enums.Sabor;
import com.infnet.projeto.enums.Tipo;
import com.infnet.projeto.model.Massa;

public class MassaDTO implements Serializable {
	
	private static final long serialVersionUID = -1362479649356066288L;
	
	@NotBlank
	private String descricao;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sabor sabor;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Borda borda;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	public static MassaDTO create(Massa massa) {
		return new ModelMapper().map(massa, MassaDTO.class);
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

}
