package com.itau.ecom.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.itau.ecom.DTO.DetalheProdutoResponse;
import com.itau.ecom.DTO.ProdutoRequest;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.exception.ApiErroException;
import com.itau.ecom.repository.UsuarioJpaRepository;
import com.itau.ecom.security.UsuarioSecurity;

@Service
public class ProdutoService {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioJpaRepository usuarioJpaRepository;
	
	public Long novoProduto(@Valid ProdutoRequest request) {
		
		Produto produto = request.toModel(manager, usuarioJpaRepository.findByEmail(UsuarioSecurity.authenticated().getUsername()).get());
		
		manager.persist(produto);
		
		return produto.getId();
	}

	public DetalheProdutoResponse detalheProduto(Long idProduto) {

		Produto produto = manager.find(Produto.class, idProduto);
		
		if(produto.equals(null)) {
			throw new ApiErroException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
		}
		
		return produto.toDetalheResponse();
	}

	
	
}
