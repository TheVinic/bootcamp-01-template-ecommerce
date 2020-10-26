package com.itau.ecom.enums;

public enum GatewayPagamentoEnum {
	DEFAULT(0, "Defalt", "", "", ""),
	PAYPAL(1, "PayPal", "paypal.com/", "?redirectUrl=", "/v1/compras/{id}/retorno-paypal"),
	PAGSEGURO(2, "PagSeguro", "pagseguro.com?returnId=", "&redirectUrl=","/v1/compras/{id}/retorno-pagseguro");
	
	private Integer id;
	private String nome;
	private String link;
	private String continuacao;
	private String redireciona;
	
	private GatewayPagamentoEnum(Integer id, String nome, String link, String continuacao,String redireciona) {
		this.id = id;
		this.nome = nome;
		this.link = link;
		this.continuacao = continuacao;
		this.redireciona = redireciona;
	}

	public GatewayPagamentoEnum InformacoesGatewayPagamento(Integer idGatewayPagamento) {
		
		for(GatewayPagamentoEnum gatewayPagamento : GatewayPagamentoEnum.values()) {
			if(idGatewayPagamento.equals(gatewayPagamento.getId())) {
				return gatewayPagamento;
			}
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getLink() {
		return link;
	}

	public String getRedireciona() {
		return redireciona;
	}

	public String getContinuacao() {
		return continuacao;
	}
	
}
