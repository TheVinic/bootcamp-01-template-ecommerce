package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.ecom.entity.Pergunta;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;

public class PerguntaRequest {

	@NotBlank
	@JsonProperty("titulo")
	private String titulo;

	public Pergunta toModel(@NotNull Produto produto, @NotNull Usuario usuario) {
		return new Pergunta(titulo, produto, usuario);
	}
	
}
