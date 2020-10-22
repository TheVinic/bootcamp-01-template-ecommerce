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

import com.itau.ecom.DTO.PerguntaRequest;
import com.itau.ecom.service.PerguntaService;

@Controller
public class PerguntaController {

	@Autowired
	private PerguntaService perguntaService;
	
	@PostMapping("/v1/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> NovaPergunta (@RequestBody @Valid PerguntaRequest request, @PathVariable("id") Long id, UriComponentsBuilder builder){
		
		perguntaService.NovaPergunta(id, request);
		
		return null;
		
	}
	
}
