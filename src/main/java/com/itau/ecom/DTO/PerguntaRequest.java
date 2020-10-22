package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.itau.ecom.entity.Pergunta;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	public Pergunta toModel(@NotNull Produto produto, @NotNull Usuario usuario) {
		return new Pergunta(titulo, produto, usuario);
	}
	
}
