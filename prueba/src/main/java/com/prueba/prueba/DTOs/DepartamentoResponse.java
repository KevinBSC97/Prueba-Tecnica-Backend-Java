package com.prueba.prueba.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoResponse {
	private Long id;
	private String nombre;
	private String estado;
	private String mensaje;
}
