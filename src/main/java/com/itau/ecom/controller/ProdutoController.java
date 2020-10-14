package com.itau.ecom.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.ProdutoRequest;
import com.itau.ecom.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping("/v1/produtos")
	@Transactional
	public ResponseEntity<?> NovoProduto (@RequestBody @Valid ProdutoRequest request, UriComponentsBuilder builder){
		
		Long idProduto = produtoService.novoProduto(request);
		
		URI enderecoConsulta = builder.path("/v1/usuarios/{id}").build(idProduto);

		return ResponseEntity.created(enderecoConsulta).build();
		
	}
	
}
