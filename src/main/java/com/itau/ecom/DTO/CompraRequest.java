package com.itau.ecom.DTO;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.ecom.entity.Compra;
import com.itau.ecom.entity.Produto;
import com.itau.ecom.entity.Usuario;
import com.itau.ecom.enums.GatewayPagamentoEnum;
import com.itau.ecom.enums.StatusEnum;
import com.itau.ecom.security.UsuarioSecurity;

public class CompraRequest {

	@JsonProperty("id_gatewya_pagamento")
	@NotNull
	private Integer idGatewayPagamento;

	@JsonProperty("id_produto")
	@NotNull
	private Long idProduto;

	@JsonProperty("quantidade")
	@NotNull
	private Long quantidade;

	public Integer getIdGatewayPagamento() {
		return idGatewayPagamento;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Compra toModel(EntityManager manager) {

		GatewayPagamentoEnum gatewayPagamentoEnum = GatewayPagamentoEnum.DEFAULT;
		
		Produto produto = manager.find(Produto.class, this.idProduto);

		produto = produto.diminuiEstoque(quantidade, manager);

		Usuario usuario = manager.find(Usuario.class, UsuarioSecurity.authenticated().getId());

		return new Compra(gatewayPagamentoEnum.InformacoesGatewayPagamento(idGatewayPagamento), produto, quantidade,
				usuario, StatusEnum.INICIADA);
	}

}
