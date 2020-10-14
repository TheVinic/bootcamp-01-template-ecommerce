package com.itau.ecom.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itau.ecom.DTO.UsuarioResponse;
import com.itau.ecom.exception.ApiErroException;
import com.itau.ecom.repository.UsuarioJpaRepository;
import com.itau.ecom.security.UsuarioSecurity;

@Entity
//4
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String senha;
	
	@Size(min = 1)
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<Integer> perfis = new HashSet<>();

	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date instanteCadastro;

	//1
	public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha,
			UsuarioJpaRepository usuarioJpaRepository) {
		super();
		this.email = email;
		this.instanteCadastro = new Date();
		this.senha = new BCryptPasswordEncoder().encode(senha);
		adicionaPerfil(Perfil.CLIENTE);
	}

	@Deprecated
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

	public Set<Perfil> getPerfil() {
		return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
	}
	
	public void adicionaPerfil (Perfil perfil) {
		perfis.add(perfil.getCodigo());
	}

	public UserDetails toSecurity() {
		return new UsuarioSecurity(id, email, senha, getPerfil());
	}
	
	public boolean usuarioAutenticado() {
		UsuarioSecurity usuarioSecurity = UsuarioSecurity.authenticated();
		if(usuarioSecurity == null || usuarioSecurity.hasRole(Perfil.ADMIN) && id.equals(usuarioSecurity.getId())) {
			throw new ApiErroException(HttpStatus.FORBIDDEN, "Acesso negado");
		}
		return true;
	}

	public UsuarioResponse toResponse() {
		return new UsuarioResponse(id, email);
	}
	
}
