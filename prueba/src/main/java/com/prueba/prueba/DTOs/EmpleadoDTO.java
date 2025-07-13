package com.prueba.prueba.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmpleadoDTO {
	private Long id;
    private String nombres;
    private String apellidos;
    private int edad;
    private String rol;
    private double salario;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String estado;
    private String departamentoNombre;
}
