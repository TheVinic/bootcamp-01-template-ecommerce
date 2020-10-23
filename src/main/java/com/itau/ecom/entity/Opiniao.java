package com.itau.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.itau.ecom.DTO.OpiniaoResponse;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	@Deprecated
	public Opiniao() {
	}

	public Opiniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank String descricao,
			Produto produto, Usuario usuario) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public OpiniaoResponse toResponse() {
		return new OpiniaoResponse(id, nota, titulo, descricao);
	}

}
