package com.itau.ecom.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import com.itau.ecom.exception.ApiErroException;
import com.itau.ecom.repository.CategoriaJpaRepository;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {}
	
	public Categoria(@NotBlank String nome, Long idCategoriaMae, CategoriaJpaRepository categoriaJpaRepository) {
		super();
		this.nome = nome;
		
		if(idCategoriaMae != null) {
			Optional<Categoria>possivelCategoriaMae = categoriaJpaRepository.findById(idCategoriaMae);
			if(possivelCategoriaMae.isPresent()) {
				this.categoriaMae = possivelCategoriaMae.get();
			}else {
				throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Categoria mãe não cadastrada.");
			}
		}else {
			this.categoriaMae = null;
		}
	}

	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
}
