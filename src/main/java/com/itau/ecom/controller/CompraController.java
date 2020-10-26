package com.itau.ecom.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.CompraRequest;
import com.itau.ecom.DTO.PagamentoPagseguroRequest;
import com.itau.ecom.DTO.PagamentoPaypalRequest;
import com.itau.ecom.entity.Compra;
import com.itau.ecom.service.CompraService;

@RestController
@Transactional
public class CompraController {

	@Autowired
	private CompraService compraService;

	@PersistenceContext
	private EntityManager manager;

	@PostMapping("/v1/compras")
	public ResponseEntity<?> NovaCompra(@RequestBody @Valid CompraRequest request, UriComponentsBuilder builder) {

		URI link = compraService.novaCompra(request, builder, manager);

		return ResponseEntity.status(HttpStatus.FOUND).location(link).body(request);

	}

	@PostMapping("/v1/compras/{id}/retorno-pagseguro")
	public ResponseEntity<?> RetornoCompraPaypal(@PathVariable("id") Long id,
			@Valid @RequestBody PagamentoPagseguroRequest request) {

		Compra compra = compraService.processaRetornoPagamento(id, request, manager);
		return ResponseEntity.ok(compra);
	}

	@PostMapping("/v1/compras/{id}/retorno-paypal")
	public ResponseEntity<?> RetornoCompraPagseguro(@PathVariable("id") Long id,
			@Valid @RequestBody PagamentoPaypalRequest request) {

		Compra compra = compraService.processaRetornoPagamento(id, request, manager);
		return ResponseEntity.ok(compra);
	}

}
