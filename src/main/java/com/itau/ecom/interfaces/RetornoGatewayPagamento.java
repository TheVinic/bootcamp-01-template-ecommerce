package com.itau.ecom.interfaces;

import com.itau.ecom.entity.Compra;
import com.itau.ecom.entity.Transacao;

public interface RetornoGatewayPagamento {

	Transacao toTransacao(Compra compra);
	
}
