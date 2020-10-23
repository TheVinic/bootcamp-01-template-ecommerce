package com.itau.ecom.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import com.itau.ecom.DTO.CaracteristicaResponse;

@Embeddable
public class Caracteristica {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public Caracteristica(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Deprecated
	public Caracteristica() {}

	public CaracteristicaResponse toResponse() {
		return new CaracteristicaResponse(nome, descricao);
	}
	
}
