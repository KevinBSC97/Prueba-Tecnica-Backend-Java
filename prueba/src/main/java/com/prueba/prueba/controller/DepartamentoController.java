package com.prueba.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.DTOs.DepartamentoDTO;
import com.prueba.prueba.DTOs.DepartamentoResponse;
import com.prueba.prueba.entity.Departamento;
import com.prueba.prueba.services.DepartamentoService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartamentoController {
	@Autowired
	private DepartamentoService departamentoService;
	
	@PostMapping("/create")
	public ResponseEntity<Departamento> create(@RequestBody DepartamentoDTO dep){
		return ResponseEntity.ok(departamentoService.createDepartamento(dep));
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<DepartamentoResponse> delete(@PathVariable Long id){
		Departamento dep = departamentoService.deleteDepartamento(id);
		
		DepartamentoResponse response = new DepartamentoResponse(
				dep.getId(),
				dep.getNombre(),
				dep.getEstado(),
				"Departamento desactivado correctamente"
			);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DepartamentoDTO>> getAll() {
		return ResponseEntity.ok(departamentoService.getAllDepartamentos());
	}
}
