package com.prueba.prueba.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.prueba.DTOs.DepartamentoDTO;
import com.prueba.prueba.entity.Departamento;
import com.prueba.prueba.entity.Empleado;
import com.prueba.prueba.repository.DepartamentoRepository;
import com.prueba.prueba.repository.EmpleadoRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	public Departamento createDepartamento(DepartamentoDTO dep) {
		if(departamentoRepository.existsByNombre(dep.getNombre())) {
			throw new IllegalStateException("Ya existe un departamento con el nombre: " + dep.getNombre());
		}
		
		Departamento departamento = new Departamento();
		
		departamento.setId(dep.getId());
		departamento.setNombre(dep.getNombre());
		departamento.setEstado("A");
		return departamentoRepository.save(departamento);
	}
	
	public Departamento deleteDepartamento(Long id) {
		Departamento dep = departamentoRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Departamento no encontrado"));
		
		if("I".equals(dep.getEstado())) {
			throw new IllegalStateException("El departamento ya está inactivo");
		}
		
		dep.setEstado("I");
		
		List<Empleado> empleados = empleadoRepository.findByDepartamentoId(id);
		for(Empleado emp : empleados) {
			emp.setEstado("I");
			emp.setFechaSalida(LocalDate.now());
		}
		
		empleadoRepository.saveAll(empleados);		
		return departamentoRepository.save(dep);
	}
	
	public List<DepartamentoDTO> getAllDepartamentos(){
		return departamentoRepository.findAll().stream()
	            .filter(d -> "A".equals(d.getEstado()))
	            .map(d -> {
	                DepartamentoDTO dto = new DepartamentoDTO();
	                dto.setId(d.getId());
	                dto.setNombre(d.getNombre());
	                return dto;
	            })
	            .collect(Collectors.toList());
	}
}
