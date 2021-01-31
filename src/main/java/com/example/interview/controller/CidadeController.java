package com.example.interview.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.interview.model.Cidade;
import com.example.interview.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	private final CidadeRepository repository;

	@Autowired
	public CidadeController(CidadeRepository pCidadeRepository) {
		this.repository = pCidadeRepository;
	}

	@GetMapping
	public Page<Cidade> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@PostMapping
	public ResponseEntity<Cidade> create(@RequestBody(required = true) Cidade cidade) {
		Cidade savedCidade = repository.save(cidade);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCidade.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/nome/{name}")
	public Page<Cidade> findByName(@PathVariable(required = true) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return repository.findByNome(name, PageRequest.of(page, size));
	}

	@GetMapping("/estado/{estado}")
	public Page<Cidade> findByEstate(@PathVariable(required = true) String estado,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return repository.findByEstado(estado, PageRequest.of(page, size));
	}
}
