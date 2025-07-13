CREATE TABLE departamento(
	id BIGINT PRIMARY KEY,
	nombre VARCHAR(100),
	estado CHAR(1)
);

CREATE TABLE empleado(
	id BIGINT PRIMARY KEY,
	nombres VARCHAR(100),
	apellidos VARCHAR(100),
	edad INT,
	rol VARCHAR(100),
	salario DOUBLE,
	fecha_ingreso DATE,
	fecha_salida DATE,
	estado CHAR(1),
	departamento_id BIGINT,
	CONSTRAINT fk_departamento FOREIGN KEY (departamento_id) references departamento(id)
);