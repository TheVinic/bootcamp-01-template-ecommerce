package com.itau.ecom.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.UsuarioRequest;
import com.itau.ecom.DTO.UsuarioResponse;
import com.itau.ecom.service.UsuarioService;

//3
@RestController
public class UsuarioController {
	
	@Autowired
	// 1
	private UsuarioService usuarioService;
	
	@PostMapping("/v1/usuarios")
	@Transactional
	// 1
	private ResponseEntity<?> NovoUsuario(@RequestBody @Valid UsuarioRequest request, UriComponentsBuilder builder) {
		
		Long idUsuario = usuarioService.novoUsuario(request);

		// 1
		URI enderecoConsulta = builder.path("/v1/usuarios/{id}").build(idUsuario);

		return ResponseEntity.created(enderecoConsulta).build();
	}

	@GetMapping("/v1/usuarios/{id}")
	private ResponseEntity<?> ConsultaUsuario(@PathVariable("id") @NotBlank Long idUsuario){
		UsuarioResponse usuario = usuarioService.ConsultaUsuario(idUsuario);
		
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
}
