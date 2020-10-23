package com.itau.ecom.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.itau.ecom.DTO.PerguntaRequest;
import com.itau.ecom.entity.Pergunta;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;
import com.itau.ecom.security.UsuarioSecurity;

@Service
public class PerguntaService {

	@PersistenceContext
	private EntityManager manager;
	
	public void NovaPergunta(Long idProduto, @Valid PerguntaRequest request) {

		Produto produto = manager.find(Produto.class, idProduto);

		UsuarioSecurity usuarioSecurity = UsuarioSecurity.authenticated();
		Usuario usuario = manager.find(Usuario.class, usuarioSecurity.getId());

		Pergunta pergunta = request.toModel(produto, usuario);
		produto.associaPergunta(pergunta);

		manager.merge(produto);
	}

}
