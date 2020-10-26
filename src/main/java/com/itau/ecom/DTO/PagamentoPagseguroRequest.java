package com.itau.ecom.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.itau.ecom.entity.Compra;
import com.itau.ecom.entity.Transacao;
import com.itau.ecom.enums.StatusRetornoPagseguroEnum;
import com.itau.ecom.interfaces.RetornoGatewayPagamento;

public class PagamentoPagseguroRequest implements RetornoGatewayPagamento{

	@NotBlank
	private String idTransacao;
	
	@NotNull
	private StatusRetornoPagseguroEnum statusRetorno;

	public PagamentoPagseguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagseguroEnum statusRetorno) {
		super();
		this.idTransacao = idTransacao;
		this.statusRetorno = statusRetorno;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(statusRetorno.normaliza(), idTransacao, compra);
	}
	
}
