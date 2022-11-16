package com.infnet.projeto.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClienteDTO {

	@NotBlank
	private String nome;
	private String cpf;
	@NotBlank
	@Email
	private String email;
	
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
