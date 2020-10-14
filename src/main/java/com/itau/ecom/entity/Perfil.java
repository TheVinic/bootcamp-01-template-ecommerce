package com.itau.ecom.entity;

import javax.persistence.Id;

import org.springframework.http.HttpStatus;

import com.itau.ecom.exception.ApiErroException;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE"),
	OUTROS(3, "ROLE_OUTROS");
	
	@Id
	private Integer codigo;
	
	private  String descricao;
		
	private Perfil(Integer id, String perfil) {
		this.codigo = id;
		this.descricao = perfil;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if(id.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Id de perfil invalido.");
	}
	
}
