package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.ecom.entity.Caracteristica;

public class CaracteristicaRequest {

	@NotBlank
	@JsonProperty(value="nome")
	private String nome;
	
	@NotBlank
	@JsonProperty(value="descricao")
	private String descricao;

	public Caracteristica toModel() {
		return new Caracteristica(nome, descricao);
	}
	
}
