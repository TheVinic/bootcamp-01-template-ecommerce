package com.itau.ecom.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itau.ecom.entity.Usuario;
import com.itau.ecom.repository.UsuarioJpaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioJpaRepository usuarioJpaRepositoy;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		Optional<Usuario> usuario = usuarioJpaRepositoy.findByEmail(email);
		if(!usuario.isPresent()) {
			throw new UsernameNotFoundException(email);
		}
		return usuario.get().toSecurity();
		
	}
	
}
