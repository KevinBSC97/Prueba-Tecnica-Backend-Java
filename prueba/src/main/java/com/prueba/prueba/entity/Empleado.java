package com.prueba.prueba.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Empleado {
	@Id
	private Long id;
	
	private String nombres;
	private String apellidos;
	private int edad;
	private String rol;
	private double salario;
	
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
}
