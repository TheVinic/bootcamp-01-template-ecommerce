package com.itau.ecom.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.itau.ecom.entity.Compra;
import com.itau.ecom.entity.Transacao;
import com.itau.ecom.enums.StatusTransacao;
import com.itau.ecom.interfaces.RetornoGatewayPagamento;

public class PagamentoPaypalRequest implements RetornoGatewayPagamento{

	@Min(0)
	@Max(1)
	private Integer status;

	@NotBlank
	private String idTransacao;

	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
		return new Transacao(statusCalculado, idTransacao, compra);
	}

}
