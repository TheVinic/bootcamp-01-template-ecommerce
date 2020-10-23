package com.itau.ecom.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.itau.ecom.DTO.PerguntaResponse;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@CreatedDate
	private LocalDateTime instanteCriacao = LocalDateTime.now();
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	@Deprecated
	public Pergunta() {
	}

	public Pergunta(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public PerguntaResponse toResponse() {
		return new PerguntaResponse(id, titulo, instanteCriacao);
	}
	
}
