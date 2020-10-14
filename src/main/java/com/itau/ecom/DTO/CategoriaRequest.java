package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.ecom.entity.Categoria;
import com.itau.ecom.repository.CategoriaJpaRepository;
import com.itau.ecom.validator.UniqueValue;

public class CategoriaRequest {

	@JsonProperty(value="nome")
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	@JsonProperty(value="id_categoria_mae")
	private Long idCategoriaMae;

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public Categoria toModel(CategoriaJpaRepository categoriaJpaRepository) {
		return new Categoria(this.nome, this.idCategoriaMae, categoriaJpaRepository);
	}
	
}
