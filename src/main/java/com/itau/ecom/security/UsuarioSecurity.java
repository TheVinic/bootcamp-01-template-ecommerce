package com.itau.ecom.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.itau.ecom.entity.Perfil;

public class UsuarioSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	public UsuarioSecurity(Long id, String email, String senha, @Size(min = 1) Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
	
	public static UsuarioSecurity authenticated() {
		try {
			return (UsuarioSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e){
			return null;
		}
	}
}
