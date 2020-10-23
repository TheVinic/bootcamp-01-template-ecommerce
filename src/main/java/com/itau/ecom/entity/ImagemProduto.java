package com.itau.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.itau.ecom.DTO.ImagemProdutoResponse;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Valid
	@NotNull
	@ManyToOne
	private Produto produto;

	@NotBlank
	@URL
	private String link;
	
	@Deprecated
	public ImagemProduto() {
		super();
	}
	
	public ImagemProduto(@NotNull @Valid Produto produto, @NotBlank @URL String link) {
		this.produto = produto;
		this.link = link;
	}

	public ImagemProdutoResponse toResponse() {
		return new ImagemProdutoResponse(id, link);
	}

}
