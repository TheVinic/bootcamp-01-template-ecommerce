package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;

public class CaracteristicaResponse {

	private String nome;
	
	private String descricao;

	public CaracteristicaResponse(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
