package com.itau.ecom.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itau.ecom.exception.ApiErroException;
import com.itau.ecom.repository.UsuarioJpaRepository;

@Entity
//5
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String senha;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date instanteCadastro;

	//1
	public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha,
			UsuarioJpaRepository usuarioJpaRepository) {
		super();
		//1
		if (usuarioJpaRepository.findByEmail(email).isPresent()) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um cadastro com este e-mail.");
		}
		this.email = email;

		this.instanteCadastro = new Date();

		this.senha = new BCryptPasswordEncoder().encode(senha);

	}

	public Usuario() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Date getInstanteCadastro() {
		return instanteCadastro;
	}

	public Long getId() {
		return id;
	}

}
