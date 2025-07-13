package com.prueba.prueba.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.DTOs.CreateEmpleadoDTO;
import com.prueba.prueba.DTOs.EmpleadoDTO;
import com.prueba.prueba.entity.Empleado;
import com.prueba.prueba.services.EmpleadoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {
	@Autowired
	private EmpleadoService empleadoService;
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody CreateEmpleadoDTO emp){
		try {
			Empleado nuevo = empleadoService.createEmpleado(emp);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);			
		} catch(RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", "Ya existe un empleado con los mismos nombres y apellidos"));
		}
	}
	
	@PostMapping("/delete/{employeeId}")
	public ResponseEntity<?> delete(@PathVariable Long employeeId){
		try {
	        empleadoService.deleteEmpleado(employeeId);
	        return ResponseEntity.ok(Map.of("mensaje", "Empleado eliminado con éxito"));
	    } catch (RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	            Map.of("mensaje", ex.getMessage())
	        );
	    }
	}
	
	@GetMapping("/highestSalary")
	public ResponseEntity<Map<String, Object>> mayorSalario() {
		Optional<Empleado> e = empleadoService.obtenerConMayorSalario();
		if(e.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "No se encontraron empleados activos"));
		}
		Empleado emp = e.get();
		return ResponseEntity.ok(Map.of("nombre", emp.getNombres() + " " + emp.getApellidos(), "salario", emp.getSalario()));
	}
	
	@GetMapping("/lowerAge")
	public ResponseEntity<Map<String, Object>> menorEdad() {
		Optional<Empleado> e = empleadoService.obtenerMasJoven();
		if(e.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "No se encontraron empleados activos"));
		}
		Empleado emp = e.get();
		return ResponseEntity.ok(Map.of("nombre", emp.getNombres() + " " + emp.getApellidos(), "edad", emp.getEdad()));
	}
	
	@GetMapping("/countLastMonth")
	public ResponseEntity<?> contarUltimoMes() {
		try {
	        Long cantidad = empleadoService.countEmpleadosUltimoMes();
	        return ResponseEntity.ok(Map.of("cantidad", cantidad));
	    } catch (RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
	            Map.of("mensaje", "Error al contar empleados del último mes", "detalle", ex.getMessage())
	        );
	    }
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		try {
	        List<EmpleadoDTO> empleados = empleadoService.getAllEmpleados();
	        return ResponseEntity.ok(empleados);
	    } catch (RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
	            Map.of("mensaje", "Error al obtener la lista de empleados", "detalle", ex.getMessage())
	        );
	    }
	}
	
	@GetMapping("/next-id")
	public ResponseEntity<Long> getNextId() {
		Long maxId = empleadoService.getNextId();
		return ResponseEntity.ok(maxId);
	}
}
