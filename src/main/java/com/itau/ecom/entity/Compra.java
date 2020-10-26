package com.itau.ecom.entity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.enums.GatewayPagamentoEnum;
import com.itau.ecom.enums.StatusEnum;
import com.itau.ecom.exception.ApiErroException;
import com.itau.ecom.interfaces.RetornoGatewayPagamento;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private GatewayPagamentoEnum gatewayPagamento;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	@Positive
	private Long quantidade;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario comprador;

	@NotNull
	private StatusEnum status;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	public Compra(@NotNull GatewayPagamentoEnum gatewayPagamentoEnum, Produto produto, @NotNull Long quantidade,
			Usuario usuario, StatusEnum status) {
		this.gatewayPagamento = gatewayPagamentoEnum;
		this.produto = produto;
		this.quantidade = quantidade;
		this.comprador = usuario;
		this.status = status;
	}

	@Deprecated
	public Compra() {
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public GatewayPagamentoEnum getGatewayPagamento() {
		return gatewayPagamento;
	}

	public URI constroiLink(UriComponentsBuilder builder) {

		String link = gatewayPagamento.getLink() + this.id + gatewayPagamento.getContinuacao()
				+ builder.path(gatewayPagamento.getRedireciona()).buildAndExpand(this.id.toString());

		try {
			URI enderecoConsulta = new URI(link);
			return enderecoConsulta;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {

		Transacao novaTransacao = request.toTransacao(this);

		if (this.transacoes.contains(novaTransacao)) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Já existe uma transação igual a essa processada " + novaTransacao);
		}

		if (!transacoesConcluidasComSucesso().isEmpty()) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Esta compra já foi concluida com sucesso.");
		}
		
		this.transacoes.add(novaTransacao);

	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		if(transacoesConcluidasComSucesso.size()>1) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Existe mais de uma transação concluida com sucesso na compra.");
		}
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

}
