package com.itau.ecom.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.ecom.DTO.UsuarioRequest;
import com.itau.ecom.DTO.UsuarioResponse;
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

	public UsuarioResponse ConsultaUsuario(@NotBlank Long idUsuario) {
		Optional<Usuario> usuario = usuarioJpaRepository.findById(idUsuario);
		if(usuario.isPresent() && usuario.get().usuarioAutenticado()) {
			return usuario.get().toResponse();
		}
		return null;
	}
	
	

}
