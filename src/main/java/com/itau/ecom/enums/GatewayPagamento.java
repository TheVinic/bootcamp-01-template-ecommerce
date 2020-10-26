package com.itau.ecom.enums;

public class GatewayPagamento {
	
	private Integer id;
	private String nome;
	private String link;
	private String redireciona;

	public GatewayPagamento(Integer id, String nome, String link, String redireciona) {
		this.id = id;
		this.nome = nome;
		this.link = link;
		this.redireciona = redireciona;
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
	
}
