package com.example.interview.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.interview.model.Cliente;
import com.example.interview.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private final ClienteRepository repository;

	@Autowired
	public ClienteController(ClienteRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping
	public Page<Cliente> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getOne(@PathVariable("id") Long id) {
		Optional<Cliente> opt = repository.findById(id);
		if (opt.isPresent())
			return ResponseEntity.ok(opt.get());

		throw new ResourceNotFoundException("Cliente Id " + id);
	}

	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
		Cliente savedCliente = repository.save(cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCliente.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/nome/{nome}")
	public Page<Cliente> findByName(@PathVariable(required = true) String nome, Pageable pageable) {
		return repository.findByNomeContains(nome, pageable);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Cliente> updateName(@RequestBody Cliente cliente) {
		
		Optional<Cliente> optCliente = repository.findById(cliente.getId());
		if (optCliente.isPresent()) {
			
			Cliente savedCliente = optCliente.get();
			savedCliente.setNome(cliente.getNome());			
			savedCliente = repository.save(savedCliente);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedCliente.getId()).toUri();

			return ResponseEntity.created(location).build();	
		}		
		
		throw new ResourceNotFoundException("Cliente Id " + cliente.getId());
	}

}
