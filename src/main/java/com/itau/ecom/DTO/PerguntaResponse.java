package com.itau.ecom.DTO;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class PerguntaResponse {

	private Long id;
	
	private String titulo;
	
	private LocalDateTime instanteCriacao;

	public PerguntaResponse(Long id, @NotBlank String titulo, LocalDateTime instanteCriacao) {
		this.id = id;
		this.titulo = titulo;
		this.instanteCriacao = instanteCriacao;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getInstanteCriacao() {
		return instanteCriacao;
	}
	
	
}
