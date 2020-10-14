package com.itau.ecom.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;
import com.sun.istack.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.ecom.entity.Caracteristica;

public class ProdutoRequest {

	@NotBlank
	@JsonProperty(value="nome")
	private String nome;
	
	@NotNull
	@Positive
	@JsonProperty(value="valor")
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	@JsonProperty(value="quantidade")
	private Long quantidade;
	
	@Size(min=3, max = 100)
	@JsonProperty(value="caracteristicas")
	private List<CaracteristicaRequest> caracteristicas;
	
	@NotBlank
	@Size(max = 1000)
	@JsonProperty(value="descricao")
	private String descricao;
	
	@NotNull
	@JsonProperty(value="id_categoria")
	private Long idCategoria;

	public Produto toModel(EntityManager manager, Usuario usuario) {
		Set<Caracteristica> caracteristicas = this.caracteristicas.stream().map(caracteristica -> caracteristica.toModel()).collect(Collectors.toSet());
		return new Produto(nome, valor, quantidade, caracteristicas, descricao, idCategoria, usuario, manager);
	}
	
}
