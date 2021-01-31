package com.example.interview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.interview.model.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	Page<Cliente> findByNomeContains(@Param("nome") String nome, Pageable pageable);

}
