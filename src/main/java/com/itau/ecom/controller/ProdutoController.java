package com.itau.ecom.controller;

import java.net.URI;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.ImagensRequest;
import com.itau.ecom.DTO.ProdutoRequest;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UploaderFake uploaderFake;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/v1/produtos")
	@Transactional
	public ResponseEntity<?> NovoProduto (@RequestBody @Valid ProdutoRequest request, UriComponentsBuilder builder){
		
		Long idProduto = produtoService.novoProduto(request);
		
		URI enderecoConsulta = builder.path("/v1/usuarios/{id}").build(idProduto);

		return ResponseEntity.created(enderecoConsulta).build();
		
	}
	
	@PostMapping(value = "/v1/produtos/{id}/imagens")
	@Transactional
	public ResponseEntity<?> AdicionaImagens (@PathVariable("id") Long id, @Valid ImagensRequest imagensRequest){

		Set<String> links = uploaderFake.envia(imagensRequest.getImagens());
		
		Produto produto = manager.find(Produto.class, id);
		produto.getUsuario().usuarioAutenticado();
		
		produto.associaImagens(links);
		
		manager.merge(produto);
		
		return ResponseEntity.ok(links);
		
	}
	
}
