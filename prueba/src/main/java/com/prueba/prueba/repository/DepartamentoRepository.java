package com.prueba.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.prueba.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
	boolean existsByNombre(String nombre);
}
