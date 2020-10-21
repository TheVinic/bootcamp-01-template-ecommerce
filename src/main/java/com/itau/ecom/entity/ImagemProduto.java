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

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Valid
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public ImagemProduto() {
		super();
	}

	@NotBlank
	@URL
	private String link;
	
	public ImagemProduto(@NotNull @Valid Produto produto, @NotBlank @URL String link) {
		this.produto = produto;
		this.link = link;
	}

}
