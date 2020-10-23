package com.itau.ecom.DTO;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.itau.ecom.entity.Caracteristica;
import com.itau.ecom.entity.ImagemProduto;
import com.itau.ecom.entity.Opiniao;
import com.itau.ecom.entity.Pergunta;

public class DetalheProdutoResponse {

	private Set<ImagemProdutoResponse> imagens;
	
	private String nome;
	
	private BigDecimal valor;
	
	private Set<CaracteristicaResponse> caracteristicas;
	
	private String descricao;
	
	private BigDecimal mediaNotas;
	
	private Integer totalNotas;
	
	private Set<OpiniaoResponse> opinioes;
	
	private Set<PerguntaResponse> perguntas;

	public DetalheProdutoResponse(Set<ImagemProduto> imagens, @NotBlank String nome, @Positive BigDecimal valor,
			@Size(min = 3) Set<Caracteristica> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			BigDecimal mediaNotas, Integer totalNotas, Set<Opiniao> opinioes, Set<Pergunta> perguntas) {
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.mediaNotas = mediaNotas;
		this.totalNotas = totalNotas;
		
		this.imagens = StreamSupport.stream(imagens.spliterator(), false).map(imagem -> imagem.toResponse()).collect(Collectors.toSet());
		this.caracteristicas = StreamSupport.stream(caracteristicas.spliterator(), false).map(caracteristica -> caracteristica.toResponse()).collect(Collectors.toSet());
		this.opinioes = StreamSupport.stream(opinioes.spliterator(), false).map(opiniao -> opiniao.toResponse()).collect(Collectors.toSet());
		this.perguntas = StreamSupport.stream(perguntas.spliterator(), false).map(pergunta -> pergunta.toResponse()).collect(Collectors.toSet());

	}

	public Set<ImagemProdutoResponse> getImagens() {
		return imagens;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}

	public Set<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaResponse> getPerguntas() {
		return perguntas;
	}
	
}
