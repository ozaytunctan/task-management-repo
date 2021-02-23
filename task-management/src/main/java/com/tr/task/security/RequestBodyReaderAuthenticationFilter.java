package com.tr.task.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		String authorizationHeader = request.getHeader(SecurityConstantParameter.AUTHENTICATION);
//		String username = null, token = null;
//		if (authorizationHeader != null && authorizationHeader.startsWith("Baerer ")) {
//			token = authorizationHeader.replace("Baerer ", "");
//			username = jwtProvider.extractUsername(token);
//		}
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			UserDetails userDetails = this.userService.loadUserByUsername(username);
//
//			if (userDetails!=null && jwtProvider.tokenValidate(token,userDetails)) {
//
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//			}
//		}
		doFilter(request, response, chain);
	}

}
