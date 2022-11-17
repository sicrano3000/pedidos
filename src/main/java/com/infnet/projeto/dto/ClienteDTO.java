package com.infnet.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.model.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -3384152602360043312L;
	
	@NotBlank
	private String nome;
	private String cpf;
	@NotBlank
	@Email
	private String email;
	
	public static ClienteDTO create(Cliente cliente) {
		return new ModelMapper().map(cliente, ClienteDTO.class);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
