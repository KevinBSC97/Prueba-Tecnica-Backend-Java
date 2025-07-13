--Departamentos (estado: A = activo, I = inactivo)
INSERT INTO departamento (id, nombre, estado) VALUES (1, 'Sistemas', 'A');
INSERT INTO departamento (id, nombre, estado) VALUES (2, 'Contabilidad', 'A');
INSERT INTO departamento (id, nombre, estado) VALUES (3, 'RRHH', 'I');
INSERT INTO departamento (id, nombre, estado) VALUES (4, 'People', 'A');

--Empleados
INSERT INTO empleado (id, nombres, apellidos, edad, rol, salario, fecha_ingreso, fecha_salida, estado, departamento_id)
VALUES (1, 'Luis', 'Pérez', 22, 'Analista', 500.00, '2021-02-10', null, 'A', 1);

INSERT INTO empleado (id, nombres, apellidos, edad, rol, salario, fecha_ingreso, fecha_salida, estado, departamento_id)
VALUES (2, 'Maria', 'Gonzalez', 25, 'Contadora', 600.00, '2020-03-11', null, 'A', 1);

INSERT INTO empleado (id, nombres, apellidos, edad, rol, salario, fecha_ingreso, fecha_salida, estado, departamento_id)
VALUES (3, 'Pedro', 'Gómez', 30, 'RRHH', 700.00, '2020-03-11', '2024-05-20', 'I', 2);

INSERT INTO empleado (id, nombres, apellidos, edad, rol, salario, fecha_ingreso, fecha_salida, estado, departamento_id)
VALUES (4, 'José', 'López', 20, 'Asistente', 550.00, '2024-03-11', null, 'A', 2);
