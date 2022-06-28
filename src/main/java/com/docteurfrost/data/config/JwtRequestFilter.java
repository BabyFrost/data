package com.docteurfrost.data.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.docteurfrost.data.exception.UnauthorizedRequestException;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.service.UtilisateurService;

//@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	RestTemplate rest = new RestTemplate();
	
	@Autowired
	UtilisateurService utilisateurService;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		System.out.println("XXXXXXXXXXX");
		final String requestTokenHeader = request.getHeader("Authorization");

		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("JWT : "+jwtToken);
			String username = rest.postForObject("http://localhost:5050/authenticateToken", jwtToken, String.class);
			System.out.println("Username : "+username);
			Utilisateur utilisateur = utilisateurService.getUtilisateurByUsername(username);
		} else {
			System.out.println("JWT Token does not begin with Bearer String");
			throw new UnauthorizedRequestException("Unauthorized Request");
		}

		chain.doFilter(request, response);
	}

}
