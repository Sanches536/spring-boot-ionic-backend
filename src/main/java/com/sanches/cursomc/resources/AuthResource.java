package com.sanches.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanches.cursomc.dto.EmailDTO;
import com.sanches.cursomc.security.JWTUtil;
import com.sanches.cursomc.security.UserSS;
import com.sanches.cursomc.services.AuthService;
import com.sanches.cursomc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService Service;
	
	@RequestMapping(value = "/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToke(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generationToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto){
		Service.SendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

}
