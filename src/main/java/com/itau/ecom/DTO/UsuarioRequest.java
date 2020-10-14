package com.itau.ecom.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.itau.ecom.entity.Usuario;
import com.itau.ecom.repository.UsuarioJpaRepository;
import com.itau.ecom.validator.UniqueValue;

//3
public class UsuarioRequest {

	@NotBlank	
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	
	@NotBlank
	@Length(min=6)
	private String senha;
	//1
	public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public UsuarioRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	//2
	public Usuario toUsuario(UsuarioJpaRepository usuarioJpaRepository) {

		return new Usuario(email, senha, usuarioJpaRepository);
		
	}
	
}
