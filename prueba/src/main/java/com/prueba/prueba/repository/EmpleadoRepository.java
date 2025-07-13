package com.prueba.prueba.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prueba.prueba.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
	Optional<Empleado> findByNombresAndApellidos(String nombres, String apellidos);
	Optional<Empleado> findFirstByEstadoOrderBySalarioDesc(String estado);
	Optional<Empleado> findFirstByEstadoOrderByEdadAsc(String estado);
	long countByFechaIngresoBetween(LocalDate inicio, LocalDate fin);
	@Query("SELECT MAX(e.id) FROM Empleado e")
	Optional<Long> findMaxId();
	List<Empleado> findByDepartamentoId(Long departamentoId);
}
