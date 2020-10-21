package com.itau.ecom.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.itau.ecom.DTO.OpiniaoRequest;
import com.itau.ecom.service.OpiniaoService;

@Controller
public class OpiniaoController {

	@Autowired
	private OpiniaoService opiniaoService;
	
	@PostMapping("/v1/produtos/{id}/opinioes")
	@Transactional
	public ResponseEntity<?> NovaOpiniao (@RequestBody @Valid OpiniaoRequest request, @PathVariable("id") Long id, UriComponentsBuilder builder){
		
		opiniaoService.NovaOpiniao(id, request);

		return ResponseEntity.ok().build();
	}
	
}
