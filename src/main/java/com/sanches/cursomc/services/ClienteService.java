package com.sanches.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanches.cursomc.domain.Cliente;
import com.sanches.cursomc.repositories.ClienteRepository;
import com.sanches.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository categoriaRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
