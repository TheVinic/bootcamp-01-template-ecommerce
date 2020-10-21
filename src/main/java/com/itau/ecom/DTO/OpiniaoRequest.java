package com.itau.ecom.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.ecom.entity.Opiniao;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;

public class OpiniaoRequest {

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	@JsonProperty(value="nota")
	private Integer nota;
	
	@NotBlank
	@JsonProperty(value="titulo")
	private String titulo;
	
	@NotBlank
	@JsonProperty(value="descricao")
	private String descricao;

	public Opiniao toModel(Produto produto, Usuario usuario) {
		return new Opiniao(nota, titulo, descricao, produto, usuario) ;
	}
	
}
