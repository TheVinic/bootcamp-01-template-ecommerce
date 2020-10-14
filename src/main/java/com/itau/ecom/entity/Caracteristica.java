package com.itau.ecom.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Caracteristica {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String valor;

	public Caracteristica(@NotBlank String nome, @NotBlank String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	@Deprecated
	public Caracteristica() {}
	
}
