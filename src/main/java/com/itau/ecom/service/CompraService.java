package com.itau.ecom.service;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.CompraRequest;
import com.itau.ecom.entity.Compra;
import com.itau.ecom.interfaces.RetornoGatewayPagamento;
import com.itau.ecom.utils.NotaFiscal;

@Service
public class CompraService {

	@Autowired
	private NotaFiscal notaFiscal;

	public URI novaCompra(@Valid CompraRequest request, UriComponentsBuilder builder, EntityManager manager) {
					
		Compra compra = request.toModel(manager);
		
		manager.persist(compra);
		
		return compra.constroiLink(builder);
	}

	public Compra processaRetornoPagamento(Long idCompra, @Valid RetornoGatewayPagamento request, EntityManager manager) {
		
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(request);
		manager.merge(compra);
		
		if(compra.processadaComSucesso()) {
			notaFiscal.processa(compra);
		}
		
		return compra;
	}

}
