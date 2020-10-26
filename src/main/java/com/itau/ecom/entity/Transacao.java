package com.itau.ecom.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.itau.ecom.enums.StatusTransacao;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private StatusTransacao status;
	
	@NotBlank
	private String idTransacaoGateway;
	
	@CreatedDate
	private LocalDateTime instanteCriacao;
	
	@ManyToOne
	@NotNull
	@Valid
	private Compra compra;
	
	@Deprecated
	public Transacao() {
	}

	public Transacao(StatusTransacao status, @NotBlank String idTransacaoGateway, @NotNull @Valid Compra compra) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.instanteCriacao = LocalDateTime.now();
		this.compra = compra;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}

}
