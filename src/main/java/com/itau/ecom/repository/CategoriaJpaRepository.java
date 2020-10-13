package com.itau.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itau.ecom.entity.Categoria;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByNome(String nome);
	
}
