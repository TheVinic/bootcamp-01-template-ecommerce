package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public class ImagemProdutoResponse {

	private long id;
	
	private String link;

	public ImagemProdutoResponse(long id, @NotBlank @URL String link) {
		this.id = id;
		this.link = link;
	}

	public long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}
	
}
