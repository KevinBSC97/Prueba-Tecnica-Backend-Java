package com.prueba.prueba.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Departamento {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String estado;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Empleado> empleados;
}
