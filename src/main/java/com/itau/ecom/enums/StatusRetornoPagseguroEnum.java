package com.itau.ecom.enums;

public enum StatusRetornoPagseguroEnum {

	SUCESSO, ERRO;
	
	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		return StatusTransacao.erro;
	}
	
}
