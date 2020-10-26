package com.itau.ecom.controller;

import org.springframework.web.bind.annotation.PostMapping;

public class OutrosSistemasController {

	@PostMapping("/v1/notas-fiscais")
	public void criaNota(Long idCompra, Long idComprador) throws InterruptedException {
		System.out.println("Criando nota para " + idCompra + " do comprador " + idComprador);
		Thread.sleep(150);
	}
	

	@PostMapping("/v1/rankinkg")
	public void ranking(Long idCompra, Long idVendedor) throws InterruptedException {
		System.out.println("Criando ranking para " + idCompra + " do vendedor " + idVendedor);
		Thread.sleep(150);
	}
}
