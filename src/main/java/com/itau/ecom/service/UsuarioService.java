package com.itau.ecom.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.ecom.DTO.UsuarioRequest;
import com.itau.ecom.entity.Usuario;
import com.itau.ecom.repository.UsuarioJpaRepository;

@Service
//3
public class UsuarioService {

	@Autowired
	//1
	private UsuarioJpaRepository usuarioJpaRepository;
	
	//1
	public Long novoUsuario(@Valid UsuarioRequest request) {
		//1
		Usuario usuario = request.toUsuario(usuarioJpaRepository);
		
		usuarioJpaRepository.save(usuario);
		
		return usuario.getId();
	}

}
