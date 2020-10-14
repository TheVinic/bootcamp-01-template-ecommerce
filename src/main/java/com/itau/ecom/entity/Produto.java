package com.itau.ecom.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import com.itau.ecom.exception.ApiErroException;
import com.sun.istack.NotNull;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private Long quantidade;
	
	@Size(min=3)
	@ElementCollection
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date instanteCadastro;

	public Produto(@NotBlank String nome, @Positive BigDecimal valor, @PositiveOrZero Long quantidade,
			@Size(min = 3) Set<Caracteristica> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			Long idCategoria, Usuario usuario, EntityManager manager) {
		super();
		
		Categoria possivelCategoria = manager.find(Categoria.class, idCategoria);
		if(possivelCategoria == null) {
			throw new ApiErroException(HttpStatus.BAD_REQUEST, "Categoria não cadastrada");
		}
		this.categoria = possivelCategoria;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.instanteCadastro = new Date();
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}
	
}
