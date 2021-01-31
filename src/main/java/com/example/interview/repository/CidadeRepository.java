package com.example.interview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.interview.model.Cidade;

@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {

	Page<Cidade> findByNome(@Param("nome") String nome, Pageable pageable);

	Page<Cidade> findByEstado(@Param("estado") String estado, Pageable pageable);

}
