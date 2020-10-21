package com.itau.ecom.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.itau.ecom.DTO.OpiniaoRequest;
import com.itau.ecom.entity.Opiniao;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;
import com.itau.ecom.security.UsuarioSecurity;

@Service
public class OpiniaoService {

	@PersistenceContext
	private EntityManager manager;
	
	public void NovaOpiniao(Long idProduto, OpiniaoRequest request) {
		
		Produto produto = manager.find(Produto.class, idProduto);
		
		UsuarioSecurity usuarioSecurity = UsuarioSecurity.authenticated();
		Usuario usuario = manager.find(Usuario.class, usuarioSecurity.getId());
		
		Opiniao opiniao = request.toModel(produto, usuario);
		produto.associaOpiniao(opiniao);
		
		manager.merge(produto);
		
	}

}
