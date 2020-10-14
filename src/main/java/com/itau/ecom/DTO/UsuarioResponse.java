package com.itau.ecom.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioResponse {

	@NotBlank
	private Long id;
	
	@NotBlank	
	@Email
	private String email;
	//1
	public UsuarioResponse(@NotBlank Long id, @NotBlank @Email String email) {
		super();
		this.id = id;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
}
