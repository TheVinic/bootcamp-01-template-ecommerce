package com.itau.ecom.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/auth/refresh_token")
	public ResponseEntity<?> refreshToken(HttpServletResponse response){
		UsuarioSecurity usuario = UsuarioSecurity.authenticated();
		String token = jwtUtil.generationToken(usuario.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
			
			
}
