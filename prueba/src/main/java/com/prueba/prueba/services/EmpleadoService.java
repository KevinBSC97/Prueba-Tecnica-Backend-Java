package com.prueba.prueba.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.prueba.DTOs.CreateEmpleadoDTO;
import com.prueba.prueba.DTOs.EmpleadoDTO;
import com.prueba.prueba.entity.Departamento;
import com.prueba.prueba.entity.Empleado;
import com.prueba.prueba.repository.DepartamentoRepository;
import com.prueba.prueba.repository.EmpleadoRepository;
@Service
public class EmpleadoService {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public Empleado createEmpleado(CreateEmpleadoDTO dto) {
		if(dto.getId() == null) {
			throw new IllegalArgumentException("El Id del empleado es requerido");
		}
		
		Departamento dep = departamentoRepository.findById(dto.getDepartamentoId())
				.orElseThrow( () -> new RuntimeException("Departamento no encontrado"));
		
		if(dto.getId() != null && empleadoRepository.existsById(dto.getId())) {
			throw new RuntimeException("El Id " + dto.getId() + " ya existe");
		}
		
		Optional<Empleado> empleadoExistente = empleadoRepository.findByNombresAndApellidos(dto.getNombres(), dto.getApellidos());
		if(empleadoExistente.isPresent()) {
			throw new RuntimeException("Ya existe un empleado con los mismos nombres y apellidos");
		}
		
		Empleado empleado = new Empleado();
		
		empleado.setId(dto.getId());
		empleado.setNombres(dto.getNombres());
		empleado.setApellidos(dto.getApellidos());
		empleado.setEdad(dto.getEdad());
		empleado.setRol(dto.getRol());
		empleado.setSalario(dto.getSalario());
		empleado.setFechaIngreso(dto.getFechaIngreso());
		empleado.setEstado("A");
		empleado.setDepartamento(dep);
		
		return empleadoRepository.save(empleado);
	}
	
	public Empleado deleteEmpleado(Long id) {
		Empleado emp = empleadoRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Empleado no encontrado"));
		
		if("I".equals(emp.getEstado())) {
			throw new IllegalStateException("El empleado ya está inactivo");
		}
		
		emp.setEstado("I");
		emp.setFechaSalida(LocalDate.now());
		return empleadoRepository.save(emp);
	}
	
	public Optional<Empleado> obtenerConMayorSalario() {
		return empleadoRepository.findFirstByEstadoOrderBySalarioDesc("A").stream().max(Comparator.comparingDouble(Empleado::getSalario));
	}
	
	public Optional<Empleado> obtenerMasJoven() {
		return empleadoRepository.findFirstByEstadoOrderByEdadAsc("A").stream().min(Comparator.comparingInt(Empleado::getEdad));
	}
	
	public long countEmpleadosUltimoMes() {
		LocalDate hoy = LocalDate.now();
		LocalDate haceUnMes = hoy.minusMonths(1);
		return empleadoRepository.findAll().stream()
				.filter(e -> e.getFechaIngreso() != null)
				.filter(e -> !e.getFechaIngreso().isBefore(haceUnMes))
				.filter(e -> !e.getFechaIngreso().isAfter(hoy))
				.count();
	}
	
	public List<EmpleadoDTO> getAllEmpleados(){
		return empleadoRepository.findAll().stream().map(e -> {
			EmpleadoDTO dto = new EmpleadoDTO();
			dto.setId(e.getId());
			dto.setNombres(e.getNombres());
	        dto.setApellidos(e.getApellidos());
	        dto.setEdad(e.getEdad());
	        dto.setRol(e.getRol());
	        dto.setSalario(e.getSalario());
	        dto.setFechaIngreso(e.getFechaIngreso());
	        dto.setFechaSalida(e.getFechaSalida());
	        dto.setEstado(e.getEstado());
	        dto.setDepartamentoNombre(e.getDepartamento().getNombre());
	        return dto;
		}).collect(Collectors.toList());
	}
	
	public Long getNextId() {
		return empleadoRepository.findMaxId().orElse(0L) + 1;
	}
}
