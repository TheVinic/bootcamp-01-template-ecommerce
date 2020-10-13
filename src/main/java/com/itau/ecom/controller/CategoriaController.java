package com.itau.ecom.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.CategoriaRequest;
import com.itau.ecom.entity.Categoria;
import com.itau.ecom.repository.CategoriaJpaRepository;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaJpaRepository categoriaJpaRepository;
	
	@PostMapping("/v1/categorias")
	public ResponseEntity<?> NovaCategoria(@RequestBody @Valid CategoriaRequest request, UriComponentsBuilder builder){
		
		Categoria categoria = request.toModel(categoriaJpaRepository);
		
		categoriaJpaRepository.save(categoria);
		
		URI enderecoConsulta = builder.path("/v1/categorias/{id}").build(categoria.getId());
		
		return ResponseEntity.created(enderecoConsulta).build();
		
	}
	
}
