package com.itau.ecom.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoResponse {

	private Long id;

	private Integer nota;
	
	private String titulo;

	private String descricao;

	public OpiniaoResponse(Long id, @NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank String descricao) {
		this.id = id;
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
